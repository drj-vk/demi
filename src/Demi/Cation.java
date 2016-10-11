package Demi;

import static java.lang.Math.exp;
import static java.lang.Math.log;
import static java.lang.Math.sqrt;

/**
 * Created by diederik.van.krieken on 13-9-2016.
 */
public class Cation extends Agent {
    /*
    * Cation contains 6 filters
    * */
    int n_filters = 6;

//      1. Knowledge of cation head about the sub-agents:
//          {C1, ..., C6} can process a amount of water
//          {C1, ..., C6} needs to be cleaned after b water
//          {C1, ..., C6} has filtered c amount of water
//          {C1, ..., C6} needs d acid to clean
//          {C1, ..., C6} needs e time to clean
//
//      2. Currently x amount of water being filtered
//      3. Currently Z \in {A1, ..., A6} filter being used for water filtering
//      4. Currently Y \in {A1, ..., A6} filter being used for cleaning
//      5. Currently w amount of acid being used for cleaning

//    private double[] a = new double[n_filters];
//    private double[] b = new double[n_filters];
//    private double[] c = new double[n_filters];
//    private double[] d = new double[n_filters];
//    private double[] e = new double[n_filters];
//    private int[] z = new int[n_filters]; //filter being used for water cleaning, 1 or zero when not being used
//    private int[] y = new int[n_filters]; //filter being cleaned, 1 or 0 when not being used
//
//    private double x,w;// knowledge about the water and acid being used

    public double getUtility() {
        return utility;
    }

    public void setUtility(double utility) {
        this.utility = utility;
    }

    private double utility;

    @Override
    public double utility(State offer){
        double value;
        value = exp(offer.getAcid() - offer.getWater())/exp(1);
        return value;
    }


    /*
    * Calculation from point to line
    *
    * Indifference curve van e^(x-y) is gelijk aan y = x +log(utility)
    * Oftewel 0 = x -y +log(utility)
    * a = - 1; b = +1; c = -1;
    * x = \frac{b(bx_0 - ay_0)-ac}{a^2 + b^2}
    * y = \frac{a(-bx_0 + ay_0)-bc}{a^2 + b^2}
    *
    * x = 0.5*(x+y-(log(u)+1))
    * y = 0.5*(x+y+(log(u)+1))
    * */


    public State pointOnConcessionLine(State x, double u){
        double first_cor = x.getWater();
        double second_cor = x.getAcid();
        State xPlusOne = new State();
        xPlusOne.setWater(0.5*(-(log(u)+1)+first_cor+second_cor));
        xPlusOne.setAcid(0.5*((log(u)+1)+first_cor+second_cor));
        return xPlusOne;
    }

    public State pointOnConcessionLine(State x){
        double first_cor = x.getWater();
        double second_cor = x.getAcid();
        State xPlusOne = new State();
        xPlusOne.setWater(0.5*(-(log(this.utility)+1)+first_cor+second_cor));
        xPlusOne.setAcid(0.5*((log(this.utility)+1)+first_cor+second_cor));
        return xPlusOne;
    }

    // Anion reservation function is base  = water^2
    // So base has to be equal or less than sqrt(water)

    @Override
    public boolean reservationCurveCheck(State offer){
        double first_cor = offer.getWater();
        double second_cor = offer.getAcid();
        if (second_cor <= sqrt(first_cor)) {
            return true;
        }else{
            return false;
        }
    }

}