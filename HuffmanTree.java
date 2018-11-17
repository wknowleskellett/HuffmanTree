package huffman;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.LinkedList;

public class HuffmanTree<K> {
    private boolean isLeaf;
    private HuffmanTree<K> left;
    private HuffmanTree<K> right;
    private double frequency;
    private K value;

    private HuffmanTree(double f, K item) {
	frequency = f;
	value = item;
	isLeaf = true;
    }

    private HuffmanTree(HuffmanTree<K> left, HuffmanTree<K> right) {
	frequency = left.frequency + right.frequency;
	this.left = left;
	this.right = right;
	isLeaf = false;
    }

    // return a list of the values designated by a particular binary set
    public List<K> get(int[] nums) throws IOException {
	if (isLeaf) {
	    throw new RuntimeException("Tree contains only one element.");
	}
	LeftOver workList = getRecursive(nums);
	LinkedList<K> retVal = new LinkedList<K>();
	retVal.add(workList.item);
	while (workList.unused.length > 0) {
	    workList = getRecursive(workList.unused);
	    retVal.add(workList.item);
	}

	/*
	 * ArrayList<K> retVal = new ArrayList<K>(soonRet.size()); for (K item :
	 * soonRet) { retVal.add(item); }
	 */
	return retVal;
    }

    private LeftOver getRecursive(int[] nums) throws IOException {
	if (isLeaf) {
	    return new LeftOver(value, nums);
	}
	if (nums.length == 0) {
	    throw new IOException("Input does not match tree.");
	}
	int[] newNums = Arrays.copyOfRange(nums, 1, nums.length);
	LeftOver retVal;

	switch (nums[0]) {
	case 0:
	    retVal = left.getRecursive(newNums);
	    break;
	case 1:
	    retVal = right.getRecursive(newNums);
	    break;
	default:
	    throw new IOException("Input int[] contains nonbinary data.");
	}

	return retVal;
    }

    private class LeftOver {
	K item;
	int[] unused;

	LeftOver(K item, int[] unused) {
	    this.item = item;
	    this.unused = unused;
	}
    }

    // filter the output of the recursive getBackwardsCodeTable() for each value to
    // be forwards
    public Map<K, int[]> getCodeTable() {
	Map<K, ArrayList<Integer>> soonRet = getBackwardsCodeTable(1);
	Map<K, int[]> retVal = new HashMap<K, int[]>();
	for (K key : soonRet.keySet()) {
	    ArrayList<Integer> oldVal = soonRet.get(key);
	    int[] newVal = new int[oldVal.size()];
	    for (int i = 0; i < newVal.length; i++) {
		int temp = oldVal.remove(oldVal.size() - 1);
		newVal[i] = temp;
	    }
	    retVal.put(key, newVal);
	}
	return retVal;
    }

    // Recursively get the table of references to each element of the table
    private Map<K, ArrayList<Integer>> getBackwardsCodeTable(int size) {
	if (isLeaf) {
	    Map<K, ArrayList<Integer>> retVal = new HashMap<K, ArrayList<Integer>>(size);
	    retVal.put(value, new ArrayList<Integer>());
	    return retVal;
	}
	Map<K, ArrayList<Integer>> retVal = new HashMap<K, ArrayList<Integer>>();
	Map<K, ArrayList<Integer>> leftMap = left.getBackwardsCodeTable(size + 1);
	Map<K, ArrayList<Integer>> rightMap = right.getBackwardsCodeTable(size + 1);
	// implement the binary reference to each side of the tree
	for (ArrayList<Integer> val : leftMap.values()) {
	    val.add(0);
	}
	for (ArrayList<Integer> val : rightMap.values()) {
	    val.add(1);
	}
	retVal.putAll(leftMap);
	retVal.putAll(rightMap);
	return retVal;
    }

    // Return a list of

    public static <K> HuffmanTree<K> generate(Map<K, Double> items) {
	// Given a list of items, generate a HuffmanTree

	// Add all the items as their own HuffmanTree to ArrayList
	ArrayList<HuffmanTree<K>> trees = new ArrayList<HuffmanTree<K>>();
	for (K key : items.keySet()) {
	    trees.add(new HuffmanTree<K>(items.get(key), key));
	}

	// Until combining trees no longer requires comparison
	// (ie. if there are only two trees, we know which to combine)

	while (trees.size() > 2) {
	    // Find the lowest two frequencies
	    HuffmanTree<K> min = trees.get(0);
	    HuffmanTree<K> sec = trees.get(1);
	    if (min.frequency > sec.frequency) {
		HuffmanTree<K> a = sec;
		sec = min;
		min = a;
	    }
	    for (int i = 2; i < trees.size(); i++) {
		HuffmanTree<K> now = trees.get(i);
		if (now.frequency < min.frequency) {
		    sec = min;
		    min = now;
		} else if (now.frequency < sec.frequency) {
		    sec = now;
		}
	    }
	    // min and sec are the trees with the lowest two frequencies.
	    trees.remove(min);
	    trees.remove(sec);
	    trees.add(new HuffmanTree<K>(min, sec));
	    // .size() of trees has now decreased by one
	}
	return new HuffmanTree<K>(trees.get(0), trees.get(1));
    }
}
