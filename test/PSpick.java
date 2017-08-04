/**
 * 
 */
package test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @version 0.0.1
 * @since 2016/11/21
 * @author Yuki
 *
 */
public class PSpick {
	
	static double threshold = 0.5;
	static String filePath = "/Users/Yuki/forKato/p900_xdisplacement.dat";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		
		double[] u = Fileimport(filePath);
		int maxPointIndex = getMaxPointIndex(u);
		int[] peaks = findPeaks(u);
		int endPoint = getEndPoint(u, maxPointIndex);
		for (int peak : peaks)
			System.out.println(peak);
//		System.out.println(endPoint + " " + u[endPoint]);
	}
	
	/**
	 * 
	 * @param u[i]
	 * @return u[i]が最大値をとるi
	 */
	public static int getMaxPointIndex(double[] u) {
		int i = 0;
		double max = 0;
		double max2 = 0;
		for (int j = 0; j < u.length; j++)
			if (max2 < (max = u[j] * u[j])) {
				max2 = max;
				i = j;
			}
		return i;
	}
	
	/**
	 * ピーク位置（複数の）を探す. (f[a] - f[a-1]) * (f[a+1] - f[a]) < 0 の点
	 *@param u[i]
	 *@return ピーク位置のi
	 */
	private static int[] findPeaks (double[] u) {
		List<Integer> peakI = new ArrayList<>();
		for (int i = 1; i < u.length -1; i++) {
			double du1 = u[i] - u[i-1];
			double du2 = u[i+1] - u[i];
			if (du1 * du2 < 0)
				peakI.add(i);
		}
		int[] peaks = new int[peakI.size()];
		for (int i=0; i < peaks.length; i++)
			peaks[i] = peakI.get(i);
		return peaks;
	}
	
	/**
	 * 一番はじめにthresholdを超える点を返す
	 * @param u
	 * @param maxPoint
	 * @return 考える領域のおわり
	 */
	public static int getEndPoint(double[] u, int maxPoint) {
		double max = u[maxPoint];
		// ピークを探す
		int[] iPeaks = findPeaks(u);
		double minLimit = Math.abs(threshold * max);
		// System.out.println("Threshold is " + minLimit);
		for (int ipeak : iPeaks)
			if (minLimit < Math.abs(u[ipeak]))
				return ipeak;

		return maxPoint;

	}
	
	/**
	 * 
	 * @param filePath
	 * @return int配列
	 */
	public static double[] Fileimport(String filePath){
		
        double u[] = new double[3600];

        //ファイルの読み込み
        try{
            FileReader filereader = new FileReader(filePath);
            BufferedReader br = new BufferedReader(filereader);

            String str = br.readLine();
            int count = 0;

            while(str != null){
                u[count] = parseDouble(str);
                str = br.readLine();
//                System.out.println(u[count]);
                count++;
            }
            br.close();

        }catch(FileNotFoundException e){
            System.out.println(e);
        }catch(IOException e){
            System.out.println(e);
        }

        return u;

    }
	
	public static double parseDouble(String str){
        double x = 0;
//        for(int i = 0; i < str.length; i++){
        	x = Double.parseDouble(str);
//        }
        return x;
    }

}
