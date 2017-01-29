package com.example.thanhvu.maytinhbotui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText edt1;
    private TextView txtv;
    private Button btnnumber0 ,btnnumber1,btnnumber2,btnnumber3,btnnumber4,btnnumber5,btnnumber6,btnnumber7,btnnumber8,btnnumber9;
    private Button btnnumbernhan,btnnumberchia,btnnumbercong,btnnumbertru,btnnumberbang,btnnumberac,btnnumberc,btnnumbercham;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt1=(EditText)findViewById(R.id.edit_input);
        txtv=(TextView)findViewById(R.id.txtv1);
        btnnumber0=(Button)findViewById(R.id.btn0);
        btnnumber1=(Button)findViewById(R.id.btn1);
        btnnumber2=(Button)findViewById(R.id.btn2);
        btnnumber3=(Button)findViewById(R.id.btn3);
        btnnumber4=(Button)findViewById(R.id.btn4);
        btnnumber5=(Button)findViewById(R.id.btn5);
        btnnumber6=(Button)findViewById(R.id.btn6);
        btnnumber7=(Button)findViewById(R.id.btn7);
        btnnumber8=(Button)findViewById(R.id.btn8);
        btnnumber9=(Button)findViewById(R.id.btn9);
        btnnumbernhan=(Button)findViewById(R.id.btnnhan);
        btnnumberchia=(Button)findViewById(R.id.btnchia);
        btnnumbertru=(Button)findViewById(R.id.btntru);
        btnnumbercong=(Button)findViewById(R.id.btncong);
        btnnumberbang=(Button)findViewById(R.id.btnbang);
        btnnumberac=(Button)findViewById(R.id.btnac);
        btnnumberc=(Button)findViewById(R.id.btnc);
        btnnumberbang=(Button)findViewById(R.id.btnbang);
        btnnumbercham=(Button)findViewById(R.id.btncham);
        setEvenClickview();
    }
    public void setEvenClickview(){
        btnnumber0.setOnClickListener(this);
        btnnumber1.setOnClickListener(this);
        btnnumber2.setOnClickListener(this);
        btnnumber3.setOnClickListener(this);
        btnnumber4.setOnClickListener(this);
        btnnumber5.setOnClickListener(this);
        btnnumber6.setOnClickListener(this);
        btnnumber7.setOnClickListener(this);
        btnnumber8.setOnClickListener(this);
        btnnumber9.setOnClickListener(this);
        btnnumbercong.setOnClickListener(this);
        btnnumbertru.setOnClickListener(this);
        btnnumbernhan.setOnClickListener(this);
        btnnumberchia.setOnClickListener(this);
        btnnumberbang.setOnClickListener(this);
        btnnumberac.setOnClickListener(this);
        btnnumberc.setOnClickListener(this);
        btnnumbercham.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn0:
                edt1.append("0");
                break;
            case R.id.btn1:
                edt1.append("1");
                break;
            case R.id.btn2:
                edt1.append("2");
                break;
            case R.id.btn3:
                edt1.append("3");
                break;
            case R.id.btn4:
                edt1.append("4");
                break;
            case R.id.btn5:
                edt1.append("5");
                break;
            case R.id.btn6:
                edt1.append("6");
                break;
            case R.id.btn7:
                edt1.append("7");
                break;
            case R.id.btn8:
                edt1.append("8");
                break;
            case R.id.btn9:
                edt1.append("9");
                break;
            case R.id.btntru:
                edt1.append("-");
                break;
            case R.id.btncong:
                edt1.append("+");
                break;
            case R.id.btnnhan:
                edt1.append("*");
                break;
            case R.id.btnchia:
                edt1.append("/");
                break;
            case R.id.btnbang:
                DecimalFormat df = new DecimalFormat("###.#######");
                double result = 0;
                addOperation(edt1.getText().toString());
                addNumber(edt1.getText().toString());
                // Thuật toán tính toán biểu thức
                if(arrOperation.size()>=arrNumber.size() ||arrOperation.size()<1){
                    txtv.setText("Lỗi định dạng");
                }else {
                    for (int i = 0; i < (arrNumber.size() - 1); i++) {
                        switch (arrOperation.get(i)) {
                            case "+":
                                if (i == 0) {
                                    result = arrNumber.get(i) + arrNumber.get(i + 1);
                                } else {
                                    result = result + arrNumber.get(i + 1);
                                }
                                break;
                            case "-":
                                if (i == 0) {
                                    result = arrNumber.get(i) - arrNumber.get(i + 1);
                                } else {
                                    result = result - arrNumber.get(i + 1);
                                }
                                break;
                            case "*":
                                if (i == 0) {
                                    result = arrNumber.get(i) * arrNumber.get(i + 1);
                                } else {
                                    result = result * arrNumber.get(i + 1);
                                }
                                break;
                            case "/":
                                if (i == 0) {
                                    result = arrNumber.get(i) / arrNumber.get(i + 1);
                                } else {
                                    result = result / arrNumber.get(i + 1);
                                }
                                break;
                            default:
                                break;
                        }
                    }
                    txtv.setText(df.format(result) + "");
                }

                break;
            case R.id.btnac:
                edt1.setText("");

                break;
            case R.id.btnc:
                BaseInputConnection textfieldinputConnection = new BaseInputConnection(edt1, true);
                textfieldinputConnection.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
                break;
            case R.id.btncham:
                edt1.append(".");
                break;
        }

    }
    public ArrayList<Double> arrNumber;
    public ArrayList<String> arrOperation;
    public int addOperation(String input) {
        arrOperation = new ArrayList<>();

        char[] cArray = input.toCharArray();
        for (int i = 0; i < cArray.length; i++) {
            switch (cArray[i]) {
                case '+':
                    arrOperation.add(cArray[i] + "");
                    break;
                case '-':
                    arrOperation.add(cArray[i] + "");
                    break;
                case '*':
                    arrOperation.add(cArray[i] + "");
                    break;
                case '/':
                    arrOperation.add(cArray[i] + "");
                    break;
                default:
                    break;
            }
        }
        return 0;
    }
    public void addNumber(String stringInput) {
        arrNumber = new ArrayList<>();
        Pattern regex = Pattern.compile("(\\d+(?:\\.\\d+)?)");
        Matcher matcher = regex.matcher(stringInput);
        while(matcher.find()){
            arrNumber.add(Double.valueOf(matcher.group(1)));
        }
    }
}

