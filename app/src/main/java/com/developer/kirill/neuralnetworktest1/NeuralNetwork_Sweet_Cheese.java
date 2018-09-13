package com.developer.kirill.neuralnetworktest1;

import android.widget.Button;

import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.core.events.LearningEvent;
import org.neuroph.core.events.LearningEventListener;
import org.neuroph.core.learning.LearningRule;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.learning.BackPropagation;
import org.neuroph.util.TransferFunctionType;

import java.util.Arrays;


public class NeuralNetwork_Sweet_Cheese implements LearningEventListener {
    private MultiLayerPerceptron myMlPerceptron = new MultiLayerPerceptron(TransferFunctionType.SIGMOID, 5, 10, 1);
    public static NeuralNetwork_Sweet_Cheese network = new NeuralNetwork_Sweet_Cheese();

    private NeuralNetwork_Sweet_Cheese(){
        double[] weights = {1.0630481765532074, 1.5750380918976892, 0.4456903477999816,
                0.06763503456791514, 1.186727659704379, 0.5528536175034916,
                1.6272944860453193, 1.386385762186523, 0.6939687050955562,
                -1.2385309605866595, 1.456108423085059, -0.46728348536625103,
                0.14321267077495944, -1.103555096851979, -0.05162640144515674,
                1.433991618292828, -0.05492761981934995, 1.2936013111536802,
                1.6872430300182508, 1.8481573836102336, 0.088067463823882,
                -1.7310772955303861, 0.8050509034388302, -0.17453656972563197,
                0.382402559352206, 0.08967614695820264, 0.5578263030895603,
                0.40447921705286616, 0.8582795288422628, 1.0693055129639037,
                0.0838459860381992, 0.9260120981252582, 0.4141177800479176,
                0.9296360968077229, 0.8550187436513251, 0.9185210266211561,
                0.46047960398762566, 0.20113506905592088, 0.19610737335051745,
                0.5640041479580998, 0.21035038519641097, 1.2438546692836954,
                1.4417671127374836, 1.9341847741558826, 0.5662880648928021,
                -1.0733042034648668, 0.9687930347960965, -0.2616600751468785,
                1.5225583761104355, 2.486589081753592, 0.3767529336192574,
                -1.5468172986280635, 1.3828475526502628, -0.6913740061819292,
                1.130958544018927, 0.7232023099828557, 0.2454399228577952,
                0.6398643228320349, 0.5574315858305638, 0.29400073600807936,
                0.63303300509523, 1.7616045378406173, -1.8447315397660946,
                2.2322658336993784, -0.4162616030707574, -0.33418098569792565,
                -0.5836636253732401, 1.6527876671063366, 2.44692583067791,
                0.012218321111053577, -2.0857652289704256};
        myMlPerceptron.setWeights(weights);
    }
    public void collectData(int salary, int age, int labor_term, int children_m, int marriage_m) {
        double d1 = 0.0;
        double d2 = 1.0;

        double x_min_sal = 2500;
        double x_max_sal = 100000;

        double x_min_age = 18;
        double x_max_age = 99;

        double x_min_lab = 1;
        double x_max_lab = 40;

        double x_min_ch = 0;
        double x_max_ch = 1;

        double x_min_ma = 0;
        double x_max_ma = 1;
        double[] array = new double[5];
        array[0] = (((salary - x_min_sal) * (d2 - d1)) / (x_max_sal - x_min_sal)) + d1;
        array[1] = (((age - x_min_age) * (d2 - d1)) / (x_max_age - x_min_age)) + d1;
        array[2] = (((labor_term - x_min_lab) * (d2 - d1)) / (x_max_lab - x_min_lab)) + d1;
        array[3] = (((children_m - x_min_ch) * (d2 - d1)) / (x_max_ch - x_min_ch)) + d1;
        array[4] = (((marriage_m - x_min_ma) * (d2 - d1)) / (x_max_ma - x_min_ma)) + d1;
        makeInput(myMlPerceptron, array);
    }

    public void learnNetwork(int salary, int age, int labor_term, int children_m, int marriage_m, int desired_result) {
        double d1 = 0.0;
        double d2 = 1.0;

        double x_min_sal = 2500;
        double x_max_sal = 100000;

        double x_min_age = 18;
        double x_max_age = 99;

        double x_min_lab = 1;
        double x_max_lab = 40;

        double x_min_ch = 0;
        double x_max_ch = 1;

        double x_min_ma = 0;
        double x_max_ma = 1;
        double[] array = new double[5];
        array[0] = (((salary - x_min_sal) * (d2 - d1)) / (x_max_sal - x_min_sal)) + d1;
        array[1] = (((age - x_min_age) * (d2 - d1)) / (x_max_age - x_min_age)) + d1;
        array[2] = (((labor_term - x_min_lab) * (d2 - d1)) / (x_max_lab - x_min_lab)) + d1;
        array[3] = (((children_m - x_min_ch) * (d2 - d1)) / (x_max_ch - x_min_ch)) + d1;
        array[4] = (((marriage_m - x_min_ma) * (d2 - d1)) / (x_max_ma - x_min_ma)) + d1;
        DataSet trainingSet = new DataSet(5, 1);
        trainingSet.addRow(array, new double[]{desired_result});
        myMlPerceptron.setLearningRule(new BackPropagation());
        myMlPerceptron.getLearningRule().setLearningRate(0.7);
        LearningRule learningRule = myMlPerceptron.getLearningRule();
        learningRule.addListener(this);
        myMlPerceptron.learn(trainingSet);
    }

    public void run() {


    }

    public void testNeuralNetwork(org.neuroph.core.NeuralNetwork neuralNet, DataSet testSet) {
        for (DataSetRow testSetRow : testSet.getRows()) {
            neuralNet.setInput(testSetRow.getInput());
            neuralNet.calculate();
            double[] networkOutput = neuralNet.getOutput();
            System.out.print("Input: " + Arrays.toString(testSetRow.getInput()));
            System.out.println(" Output: " + Arrays.toString(networkOutput));
        }
    }

    public void makeInput(org.neuroph.core.NeuralNetwork neuralNet, double[] array) {
        neuralNet.setInput(array);
        neuralNet.calculate();
        double[] output = neuralNet.getOutput();
        double value = output[0];
        String result = "("+String.valueOf(value*100).substring(0,3)+"%) ";
        if (value > 0.65) {
            result += "Давать кредит уверенно";
        } else if (value > 0.45 && value < 0.65) {
            result += "Давать кредит осторожно";
        } else if (value < 0.45) {
            result += "Не давать кредит";
        }
        Button button = MainActivity.result;
        button.setText(result);
    }

    @Override
    public void handleLearningEvent(LearningEvent event) {
        BackPropagation bp = (BackPropagation) event.getSource();
        if (event.getEventType() != LearningEvent.Type.LEARNING_STOPPED)
            System.out.println(bp.getCurrentIteration() + ". iteration : " + bp.getTotalNetworkError());
    }

}
