/**
 * 
 */
package test;

import java.util.Arrays;

import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.transform.DftNormalization;
import org.apache.commons.math3.transform.FastFourierTransformer;
import org.apache.commons.math3.transform.TransformType;

/**
 * @version 0.0.1
 * @since 2017/06/30
 * @author Yuki
 *
 */
public class FFTsample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
	
	private static int nptsInTimeDomain;
	private static double tlen;		// time length (s)
	private static double samplingHz;	// sampling heltz (Hz)
	
	private int getNPTS() {
        int npts = (int) (tlen * samplingHz);
        int pow2 = Integer.highestOneBit(npts);
        return pow2 < npts ? pow2 * 2 : npts;
    }
	
	
	public void toTimeDomain() {
        nptsInTimeDomain = getNPTS();

        FastFourierTransformer fft = new FastFourierTransformer(DftNormalization.STANDARD);

        // pack to temporary Complex array
        Complex[] data = new Complex[nptsInTimeDomain];

        // set values for imaginary frequency  F[i] = F[N-i]
//        for (int i = 0; i < nnp - 1; i++)
//            data[nnp + 1 + i] = data[nnp - 1 - i].conjugate();

        // fast fourier transformation
        data = fft.transform(data, TransformType.FORWARD);
        
    }

}
