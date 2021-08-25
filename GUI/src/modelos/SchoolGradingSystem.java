package modelos;

public class SchoolGradingSystem extends GradingSystem{
    public void loadData(String datosEntrada, int n){
        this.numRegistros = n;
        this.matrizNotas = new double[this.numRegistros][4];
        String[] datosDivididos1 = datosEntrada.split("\n");
        
        for(int i = 0; i < this.numRegistros; i++){
            String [] datosDivididos2 = datosDivididos1[i].split(" ");
            for(int j = 0; j< datosDivididos2.length; j++ ){
                this.matrizNotas[i][j] = Double.parseDouble(datosDivididos2[j]);
            }
        }

    }
}
