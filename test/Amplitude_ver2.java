package test;

import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;

import java.io.File;

public class Amplitude_ver2 {
	PSpick pspick = new PSpick();
	public static void main(String[] args){
		double amp_sum = 0.0;
		double arrival_sum = 0.0;
		double[][] amplitude= new double[100][2];
		for(int i= 1 ; i<100;i++){
		double[] write =PSpickwrite("/home/kato/src/precompiledTC2DPSV/TC2DPSV_forUT/for_testing/Testdatabox_new_p300" + i
				+ "/p300_xdisplacement.dat");
		double max_x_norm = (write[1] / 5.7347927e-9);
		if (max_x_norm < 0){
			max_x_norm = -max_x_norm;
		}
		double arrivaltime = write[0]/(200e+6);
		amp_sum = amp_sum + max_x_norm;
		arrival_sum = arrival_sum + arrivaltime;
		amplitude[i-1][0]= max_x_norm;
		amplitude[i-1][1]= arrivaltime;
		
		
		

		//		Double max_z_norm = (max_z / 1.1286789e-8);
		/*File file = new File("/home/kato/src/precompiledTC2DPSV/TC2DPSV_forUT/for_testing/new_pmode_max_x_norm_amplitude1.dat");
		try {
			FileWriter filewriter = new FileWriter(file,true);
			filewriter.write("\r\n"+i+" "+"900 "+max_x_norm+" "+arrivaltime+"\r\n" );
			filewriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		}
		double average =amp_sum/100;
		double arrival_average = arrival_sum /100;
		System.out.println(arrival_average);
		double amplitude_hensa = 0.0;
		double arrival_hensa=0.0;
		for (int i = 0; i<100;i++){
			 arrival_hensa = arrival_hensa + Math.pow(amplitude[i][1]-arrival_average,2);
			 amplitude_hensa = amplitude_hensa + Math.pow(amplitude[i][0]-average,2);
			 
		}
		//System.out.println(arrival_hensa);
		double arrival_sigma = Math.sqrt((arrival_hensa/100));
		double amplitude_sigma = Math.sqrt(amplitude_hensa/100);
		
		//System.out.println(amplitude_sigma);
	/*	File file = new File("/home/kato/src/precompiledTC2DPSV/TC2DPSV_forUT/for_testing/new_pmode_max_x_araverage1.dat");
		try {
			FileWriter filewriter = new FileWriter(file,true);
			filewriter.write("1200 "+arrival_average+" "+arrival_sigma+" \r\n" );
			filewriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File file1 = new File("/home/kato/src/precompiledTC2DPSV/TC2DPSV_forUT/for_testing/new_pmode_max_x_ampaverage1.dat");
		try {
			FileWriter filewriter = new FileWriter(file1,true);
			filewriter.write("1200 "+average+" "+amplitude_sigma+" \r\n" );
			for(int j= 0;j<100;j++){
				
			}
			filewriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		for(int k=0;k<100;k++){
		File file_test = new File("jikantest.dat");
		try {
			FileWriter filewriter1 = new FileWriter(file_test,true);
			filewriter1.write(amplitude[k][1]+ ",");
			filewriter1.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		}
	
	public static double[] PSpickwrite(String Filepath){
		double[] u = PSpick.Fileimport(Filepath);
		int maxPointIndex = PSpick.getMaxPointIndex(u);
		// amplitude
		int endPoint = PSpick.getEndPoint(u, maxPointIndex);
		// u*5microsecond is arrival time
		System.out.println(endPoint + " " + u[endPoint]);
		double[] whattowrite = new double[2];
		whattowrite[0]=endPoint;
		whattowrite[1]=u[endPoint];
		return whattowrite;
	}
}
