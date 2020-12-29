package com.skangalis.aivis.mpi_g5_4;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    SharedPreferences mSaglabats;

    Button poga0, poga1, poga2, poga3, poga4, poga5, poga6,
            poga7, poga8, poga9, pogaAdd, pogaSub, pogaDivision,
            pogaMul, poga10, pogaC, pogaEqual, pogaMR, pogaMC, pogaMS, pogaNeg;

    EditText lauks1;

    TextView textHistory;

    BigDecimal bdValueOne, bdValueTwo, bdResult;

    boolean bAddition, bSubtract, bMultiplication, bDivision, bIsValue, bIsResult, bIsClear;

    DecimalFormat df = new DecimalFormat("#.############");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bdResult = new BigDecimal("0");

        // ielasam sharedPreferences
        mSaglabats = getSharedPreferences("prefID", Context.MODE_PRIVATE);

        // nolasam pogas un laukus
        poga0 = findViewById(R.id.poga0);
        poga1 = findViewById(R.id.poga1);
        poga2 = findViewById(R.id.poga2);
        poga3 = findViewById(R.id.poga3);
        poga4 = findViewById(R.id.poga4);
        poga5 = findViewById(R.id.poga5);
        poga6 = findViewById(R.id.poga6);
        poga7 = findViewById(R.id.poga7);
        poga8 = findViewById(R.id.poga8);
        poga9 = findViewById(R.id.poga9);
        poga10 = findViewById(R.id.poga10);
        pogaAdd = findViewById(R.id.pogaadd);
        pogaSub = findViewById(R.id.pogasub);
        pogaMul = findViewById(R.id.pogamul);
        pogaDivision = findViewById(R.id.pogadiv);
        pogaC = findViewById(R.id.pogaC);
        pogaNeg = findViewById(R.id.pogaNeg);
        pogaEqual = findViewById(R.id.pogaeql);
        pogaMR = findViewById(R.id.pogaMR);
        pogaMS = findViewById(R.id.pogaMS);
        pogaMC = findViewById(R.id.pogaMC);

        textHistory = findViewById(R.id.texthistory);

        lauks1 = findViewById(R.id.lauks1);
        setDefaults(true);

        // ciparu pogas
        poga1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearText();
                btnDigit("1");
            }
        });

        poga2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearText();
                btnDigit("2");
            }
        });

        poga3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearText();
                btnDigit("3");
            }
        });

        poga4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearText();
                btnDigit("4");
            }
        });

        poga5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearText();
                btnDigit("5");
            }
        });

        poga6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearText();
                btnDigit("6");
            }
        });

        poga7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearText();
                btnDigit("7");
            }
        });

        poga8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearText();
                btnDigit("8");
            }
        });

        poga9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearText();
                btnDigit("9");
            }
        });

        poga0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearText();
                btnDigit("0");
            }
        });

        poga10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearText();
                btnDigit(".");
            }
        });

        pogaNeg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearText();
                btnNegative();
            }
        });


        // darbiibu pogas
        pogaAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAction(v);
            }
        });

        pogaSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAction(v);
            }
        });

        pogaMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAction(v);
            }
        });

        pogaDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAction(v);
            }
        });

        pogaEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
            }
        });

        pogaC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDefaults();
                pogaC.setText(getResources().getString(R.string.ac));
                if (!bIsClear) {
                    bIsClear = true;
                } else {
                    setDefaults(true);
                }
            }
        });

        // Memory pogas
        pogaMR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSaglabats = getSharedPreferences("prefID", Context.MODE_PRIVATE);
                String saglabats = mSaglabats.getString("skaitlis", "");
                lauks1.setText(saglabats);
                showToast("Skaitlis Nolasits", true);
            }
        });

        pogaMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = mSaglabats.edit();
                editor.putString("skaitlis", lauks1.getText().toString());
                editor.apply();
                showToast("Skaitlis Saglabats", true);
            }
        });

        pogaMC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = mSaglabats.edit();
                editor.putString("skaitlis", "");
                editor.apply();
                showToast("Skaitlis Izdzests", true);
            }
        });
    }

    // palaizh, kad piespiezj ciparu pogu
    void btnDigit(String s) {
        lauks1.setText(lauks1.getText() + s);
        setDefaults(false);
    }

    // palaizh, kad veic operaciju ar skaitljiem
    void btnAction(View v) {
        bdResult = null;
        bdValueTwo = null;
        if ((bDivision || bMultiplication || bSubtract || bAddition) && !bIsResult) {
            try {
                calculate();
            } catch (Exception e) {
                setOperations();
            }
        }
        setOperations();
        if (bdValueOne == null) {
            try {
                bdValueOne = new BigDecimal(lauks1.getText().toString());
            } catch (Exception e) {
                lauks1.setText("0");
            }
        }
        int idButton = v.getId();
        if (idButton == R.id.pogaadd) {
            bAddition = true;
        } else if (idButton == R.id.pogasub) {
            bSubtract = true;
        } else if (idButton == R.id.pogadiv) {
            bDivision = true;
        } else if (idButton == R.id.pogamul) {
            bMultiplication = true;
        }
        bIsValue = true;
        lauks1.setText(null);
        setTextHistory();
    }

    // palaizh, kad piespiezj +/- pogu
    void btnNegative() {
        if (lauks1.getText().toString().contains("-")) {
            String poz = lauks1.getText().toString().replace("-", "");
            lauks1.setText(poz);
        } else {
            lauks1.setText("-" + lauks1.getText());
        }
    }


    // ar sho apreekjinam skaitli atkariibaa no ievades operatora
    void calculate() {
        if (bdValueOne != null) {
            bIsClear = false;
            if (!bIsResult) try {
                bdValueTwo = new BigDecimal(lauks1.getText().toString());
            } catch (Exception e) {
                bdValueTwo = BigDecimal.ZERO;
            }
            if (bdValueTwo == null){
                bdValueTwo = BigDecimal.ZERO;
            }

            if (bAddition) {
                bdResult = bdValueOne.add(bdValueTwo);
                String formattedResult = df.format(bdResult);
                lauks1.setText(formattedResult);
                bIsResult = true;
            } else if (bSubtract) {
                bdResult = bdValueOne.subtract(bdValueTwo);
                String formattedResult = df.format(bdResult);
                lauks1.setText(formattedResult);
                bIsResult = true;
            } else if (bMultiplication) {
                bdResult = bdValueOne.multiply(bdValueTwo);
                String formattedResult = df.format(bdResult);
                lauks1.setText(formattedResult);
                bIsResult = true;
            } else if (bDivision) {
                if ((bdValueTwo.compareTo(BigDecimal.ZERO)) == 0) {
                    lauks1.setText(bdValueOne.toString());
                    setDefaults(false);
                    setOperations();
                } else {
                    bdResult = bdValueOne.divide(bdValueTwo, 12, RoundingMode.CEILING);
                    String formattedResult = df.format(bdResult);
                    lauks1.setText(formattedResult);
                    bIsResult = true;
                }
            }
            // veic darbiibas, kad izreekjina rezultaatu
            setTextHistory();
            bdValueOne = bdResult;
            bIsResult = true;
            bIsValue = false;
            setDefaults(false);
        }
        else {bdValueOne = BigDecimal.ZERO;
        calculate();}
    }

    // notiira tekstu, kad saak rakstiit pa virsu rezultaatam
    void clearText() {
        if (bIsResult) {
            bIsResult = false;
            lauks1.setText("");
            bIsClear = false;
        }
    }

    void showToast(String s, Boolean isLong) {
        if (isLong) {
            Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
        }
    }

    void setDefaults() {
        lauks1.setText("");
    }

    void setDefaults(boolean AC) {
        if (AC) {
            setDefaults();
            setOperations();
            bdValueOne = null;
            bdValueTwo = null;
            bdResult = null;
            bIsResult = false;
            textHistory.setText("");
        }
        bIsClear = false;
        bIsValue = false;
        pogaC.setText(getResources().getString(R.string.c));
    }

    // paarbauda vai kaada operaacija jau nav iesaakta
    void setOperations() {
        bSubtract = false;
        bAddition = false;
        bDivision = false;
        bMultiplication = false;
    }

    void setTextHistory(){
        StringBuilder history = new StringBuilder();
        String op = "";
        if (bAddition) {
            op = "+ ";
        } else if (bSubtract) {
            op = "- ";
        } else if (bMultiplication) {
            op = "* ";
        } else if (bDivision) {
            op = "/ ";
        }
        if(bdValueOne.compareTo(BigDecimal.ZERO) < 0){
            history.append("(" + bdValueOne.toString() + ") ");
        }
        else history.append(bdValueOne.toString() + " ");

        history.append(op);
        if(bdValueTwo != null) {
            if(bdValueTwo.compareTo(BigDecimal.ZERO) < 0){
                history.append("(" + bdValueTwo.toString() + ")");
            }
            else history.append(bdValueTwo.toString());
        }
        if (bdResult != null) {
            if(bdResult.compareTo(BigDecimal.ZERO) < 0){
                history.append(" = (" + bdResult.toString() + ")");
            }
            else history.append(" = " + bdResult.toString());
        }
        String result = history.toString();
        textHistory.setText(result);

        if (!bAddition && !bSubtract && !bMultiplication && !bDivision && ((bdValueTwo.compareTo(BigDecimal.ZERO)) == 0)){
            textHistory.setText("ERROR: DIV / 0");
        }
    }
}
