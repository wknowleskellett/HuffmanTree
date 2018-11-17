package huffman;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class HuffmanTreeTest {

    @Test
    public void test() throws IOException {
	Map<Character, Double> items = new HashMap<Character, Double>();
	items.put('a', 0.08167);
	items.put('b', 0.01492);
	items.put('c', 0.02782);
	items.put('d', 0.04253);
	items.put('e', 0.12702);
	items.put('f', 0.02228);
	items.put('g', 0.02015);
	items.put('h', 0.06094);
	items.put('i', 0.06966);
	items.put('j', 0.00153);
	items.put('k', 0.00772);
	items.put('l', 0.04025);
	items.put('m', 0.02406);
	items.put('n', 0.06749);
	items.put('o', 0.07507);
	items.put('p', 0.01929);
	items.put('q', 0.00095);
	items.put('r', 0.05987);
	items.put('s', 0.06327);
	items.put('t', 0.09056);
	items.put('u', 0.02758);
	items.put('v', 0.00978);
	items.put('w', 0.02360);
	items.put('x', 0.00150);
	items.put('y', 0.01974);
	items.put('z', 0.00074);
	items.put('\n', 0.00000);

	HuffmanTree<Character> test = HuffmanTree.generate(items);
	Map<Character, int[]> keys = test.getCodeTable();

	System.out.println('a' + "\t" + arrayString(keys.get('a')));
	System.out.println('b' + "\t" + arrayString(keys.get('b')));
	System.out.println('c' + "\t" + arrayString(keys.get('c')));
	System.out.println('d' + "\t" + arrayString(keys.get('d')));
	System.out.println('e' + "\t" + arrayString(keys.get('e')));
	System.out.println('f' + "\t" + arrayString(keys.get('f')));
	System.out.println('g' + "\t" + arrayString(keys.get('g')));
	System.out.println('h' + "\t" + arrayString(keys.get('h')));
	System.out.println('i' + "\t" + arrayString(keys.get('i')));
	System.out.println('j' + "\t" + arrayString(keys.get('j')));
	System.out.println('k' + "\t" + arrayString(keys.get('k')));
	System.out.println('l' + "\t" + arrayString(keys.get('l')));
	System.out.println('m' + "\t" + arrayString(keys.get('m')));
	System.out.println('n' + "\t" + arrayString(keys.get('n')));
	System.out.println('o' + "\t" + arrayString(keys.get('o')));
	System.out.println('p' + "\t" + arrayString(keys.get('p')));
	System.out.println('q' + "\t" + arrayString(keys.get('q')));
	System.out.println('r' + "\t" + arrayString(keys.get('r')));
	System.out.println('s' + "\t" + arrayString(keys.get('s')));
	System.out.println('t' + "\t" + arrayString(keys.get('t')));
	System.out.println('u' + "\t" + arrayString(keys.get('u')));
	System.out.println('v' + "\t" + arrayString(keys.get('v')));
	System.out.println('w' + "\t" + arrayString(keys.get('w')));
	System.out.println('x' + "\t" + arrayString(keys.get('x')));
	System.out.println('y' + "\t" + arrayString(keys.get('y')));
	System.out.println('z' + "\t" + arrayString(keys.get('z')));
	System.out.println("\\n\t" + arrayString(keys.get('\n')));
	String temp = "010101101010101010110101110101000100110101110101010100101001010101101010101010100100";
	int[] nums = new int[temp.length()];
	for (int i = 0; i < nums.length; i++) {
	    nums[i] = Integer.parseInt("" + temp.charAt(i));
	}
	List<Character> ret = test.get(nums);

	for (Character i : ret) {
	    System.out.print("" + i);
	}

    }

    // @Test
    public void test2() {
	Map<Integer, Double> items = new HashMap<Integer, Double>();
	items.put(17, 4.0);
	items.put(732, 2.0);
	items.put(31, 70.0);
	items.put(53, 45.0);
	items.put(70, 72.0);
	items.put(81, 312.0);
	items.put(1, 700.0);
	items.put(40, 62.0);
	items.put(38, 38.0);

	HuffmanTree<Integer> test = HuffmanTree.generate(items);
	Map<Integer, int[]> keys = test.getCodeTable();

	System.out.println(17 + "\t" + arrayString(keys.get(17)));
	System.out.println(732 + "\t" + arrayString(keys.get(732)));
	System.out.println(31 + "\t" + arrayString(keys.get(31)));
	System.out.println(53 + "\t" + arrayString(keys.get(53)));
	System.out.println(70 + "\t" + arrayString(keys.get(70)));
	System.out.println(81 + "\t" + arrayString(keys.get(81)));
	System.out.println(1 + "\t" + arrayString(keys.get(1)));
	System.out.println(40 + "\t" + arrayString(keys.get(40)));
	System.out.println(38 + "\t" + arrayString(keys.get(38)));
    }

    private String arrayString(int[] these) {
	String retVal = "";
	for (int i : these) {
	    retVal += i;
	}
	return retVal;
    }

}
