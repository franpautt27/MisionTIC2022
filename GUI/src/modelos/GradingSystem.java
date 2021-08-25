package modelos;

public class GradingSystem {
    protected int numRegistros;
    protected double[][] matrizNotas;
    
    public double stat1() {
        double promedio = 0;
        double restaconprom = 0;
        double restacuadrado = 0;
        double acumresta = 0;
        double varianza = 0;
        double acumulador = 0;

        for (int i = 0; i < this.numRegistros; i++) {
            acumulador = matrizNotas[i][3] + acumulador;
        }
        promedio = acumulador / this.numRegistros;

        for (int i = 0; i < this.numRegistros; i++) {
            restaconprom = (matrizNotas[i][3] - promedio);
            restacuadrado = Math.pow(restaconprom, 2);
            acumresta = restacuadrado + acumresta;
        }

        varianza = acumresta / this.numRegistros;

        return Math.round(varianza * 100.0) / 100.0;
    }

    public double stat2() {
        double acumSobresalientes = 0;
        double porcentajeSobresalientes = 0;

        for (int i = 0; i < this.numRegistros; i++) {
            if (matrizNotas[i][3] > 8 && matrizNotas[i][3] <= 9) {
                acumSobresalientes += 1;
            }
        }

        porcentajeSobresalientes = acumSobresalientes / this.numRegistros;

        return Math.round(porcentajeSobresalientes * 100.0) / 100.0;

    }

    public String stat3() {
        double acumuladorMasculino = 0;
        double contadorMasculino = 0;
        double acumuladorFemenino = 0;
        double contadorFemenino = 0;
        double promedioMasculino = 0;
        double promedioFemenino = 0;
        String mejorGenero;

        for (int i = 0; i < this.numRegistros; i++) {
            if (matrizNotas[i][1] == 0) {
                acumuladorMasculino += matrizNotas[i][3];
                contadorMasculino += 1;
            } else {
                acumuladorFemenino += matrizNotas[i][3];
                contadorFemenino += 1;
            }

        }
        promedioMasculino = acumuladorMasculino / contadorMasculino;
        promedioFemenino = acumuladorFemenino / contadorFemenino;

        mejorGenero = promedioMasculino > promedioFemenino ? "m" : "f";

        return mejorGenero;
    }

    public String stat4(){
        int indiceMayor = 0;
        int idMejorIdiomas;
        String nombreMejorIdiomas;

        for(int i = 0 ; i < this.numRegistros; i++ ){
            if(matrizNotas[i][2] == 1 && matrizNotas[i][3] > matrizNotas[indiceMayor][3]){
                indiceMayor = i;
            }
        }
        
        idMejorIdiomas = (int) matrizNotas[indiceMayor][0];

        switch(idMejorIdiomas){
            case 1:
                nombreMejorIdiomas = "armando";
                break;
            case 2:
                nombreMejorIdiomas = "nicolas";
                break;
            case 3:
                nombreMejorIdiomas = "daniel";
                break;
            case 4:
                nombreMejorIdiomas = "maria";
                break;
            case 5:
                nombreMejorIdiomas = "marcela";
                break;
            case 6:
                nombreMejorIdiomas = "alexandra";
                break;
            default:
                nombreMejorIdiomas = "NA";
                break;
        }

        return nombreMejorIdiomas;

    }

}


