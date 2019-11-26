package com.airbattleground.jogo.math;

import java.nio.*;

import org.lwjgl.*;

public class Mat4 {
	float[][] values;

	public Mat4(float diag) {
		this.values = new float[4][4];
		for(int i = 0 ; i < 4 ; i++) {
			for (int j = 0; j < 4; j++) {
				if(i==j) this.values[i][j] = diag;
				else this.values[i][j] = 0;
			}
		}
	}
	
	public static Mat4 getScaleMatrix(float scale) {
		Mat4 scaleMat = new Mat4(scale);
		scaleMat.values[3][3] = 1;
		return scaleMat;
	}
	
	public FloatBuffer getMatrixAsBuffer() {
		FloatBuffer fb = BufferUtils.createFloatBuffer(4 * 4);
		for(int i = 0 ; i < 4 ; i++) {
			for(int j = 0 ; j < 4 ; j++) {
				fb.put(values[i][j]); //Row Major
			}
		}
		return fb.flip();
	}
}
