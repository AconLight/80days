package boost;

/**
 * Created by Wojciech on 2020-08-21.
 */
public class MyMath {
    public static float[] multiplyMatrix(float[][] a, float[] b) {
        float[] res = new float[3];
        res[0] = a[0][0]*b[0] + a[1][0]*b[1] + a[2][0]*b[2];
        res[1] = a[0][1]*b[0] + a[1][1]*b[1] + a[2][1]*b[2];
        res[2] = a[0][2]*b[0] + a[1][2]*b[1] + a[2][2]*b[2];
        return res;
    }

    public static float[][] getRotationMatrix(float alfa, float beta) {
        float[][] rot = {
                {(float) Math.cos(beta), 0, -(float) Math.sin(beta)},
                {(float) (-Math.sin(alfa)*Math.sin(beta)), (float) Math.cos(alfa), (float) (-Math.sin(alfa)*Math.cos(beta))},
                {(float) (Math.cos(alfa)*Math.sin(beta)), (float) (Math.sin(alfa)), (float) (Math.cos(alfa)*Math.cos(beta))}
        };
        return rot;
    }

    public static float[][] getRotationMatrix(float alfa, float beta, float gamma) {
        float[][] rot = {
                {(float) (Math.cos(beta)*Math.cos(gamma)), (float) (-Math.cos(beta)*Math.sin(gamma)), -(float) Math.sin(beta)},
                {(float) (Math.cos(alfa)*Math.sin(gamma)-Math.sin(alfa)*Math.sin(beta)*Math.cos(gamma)), (float) (Math.cos(alfa)*Math.cos(gamma)+Math.sin(alfa)*Math.sin(beta)*Math.sin(gamma)), (float) (-Math.sin(alfa)*Math.cos(beta))},
                {(float) (Math.sin(alfa)*Math.sin(gamma)+Math.cos(gamma)*Math.cos(alfa)*Math.sin(beta)), (float) (Math.sin(alfa)*Math.cos(gamma)-Math.sin(gamma)*Math.cos(alfa)*Math.sin(beta)), (float) (Math.cos(alfa)*Math.cos(beta))}
        };
        return rot;
    }

    public static float[] getAngles(float[] a, float[] b) {
        float alfa = (float) Math.atan2(a[1], a[2]);
        float[][] rot = getRotationMatrix(-alfa, 0);
        float[] a2 = multiplyMatrix(rot, a);
        float[] b2 = multiplyMatrix(rot, b);
        float beta = (float) Math.atan2(a2[0], a2[2]);
        float[][] rot2 = getRotationMatrix(0, -beta);
        float[] b3 = multiplyMatrix(rot2, b2);
        float gamma = (float) Math.atan2(b3[0], b3[1]);
        float[] result = {alfa, beta, gamma};
        return result;
    }
}
