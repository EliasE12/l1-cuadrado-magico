package model;

// Clase
public class CuadradoMagico {

    // Constantes


    public final static String PRIMERA_FILA = "Primera Fila";
    public final static String PRIMERA_COLUMNA = "Primera Columna";
    public final static String ULTIMA_FILA = "Útima Fila";
    public final static String ULTIMA_COLUMNA = "Última Columna";
    public final static String NO = "Noroeste";
    public final static String NE = "Noreste";
    public final static String SO = "Suroeste";
    public final static String SE = "Sureste";

    // Atributos
    private int ordenCuadrado;
    private String posicionPerimetral;
    private String sentidoLlenado;
    private int constanteMagica;
    private int[][] mCuadrado;

    // Constructores
    public CuadradoMagico() {}

    public CuadradoMagico(int ordenCuadrado, String posicionPerimetral, String sentidoLlenado) {
        this.ordenCuadrado = ordenCuadrado;
        this.posicionPerimetral = posicionPerimetral;
        this.sentidoLlenado = sentidoLlenado;
        this.constanteMagica = 0;
        mCuadrado = new int[ordenCuadrado][ordenCuadrado];
    }

    // Métodos
    public void llenarCuadradoMagico() {

        int n = 1;
        int i = 0, j = 0;

        if (posicionPerimetral.equals(PRIMERA_FILA)) {
            i = 0;
            j = ordenCuadrado / 2;
            mCuadrado[i][j] = n;
        } else if (posicionPerimetral.equals(PRIMERA_COLUMNA)) {
            i = ordenCuadrado / 2;
            j = 0;
            mCuadrado[i][j] = n;
        } else if (posicionPerimetral.equals(ULTIMA_FILA)) {
            i = ordenCuadrado - 1;
            j = ordenCuadrado / 2;
            mCuadrado[i][j] = n;
        } else {
            i = ordenCuadrado / 2;
            j = ordenCuadrado - 1;
            mCuadrado[i][j] = n;
        }

        ponerNumerosEnMatriz(i, j, n);
        calcularConstanteMagica();

        for (int in = 0; in < mCuadrado.length; in++) {
            for (int jn = 0; jn < mCuadrado[0].length; jn++) {
                System.out.print("|" + mCuadrado[in][jn] + "|");
            }
            System.out.println();
        }

    }

    public void ponerNumerosEnMatriz(int fil, int col, int posInicial) {

        switch(sentidoLlenado){
            case NE:
                llenarEnSentidoNE(fil,col,posInicial);
                break;
            case NO:
                llenarEnSentidoNO(fil,col,posInicial);
                break;
            case SE:
                llenarEnSentidoSE(fil,col,posInicial);
                break;
            case SO:
                llenarEnSentidoSO(fil,col,posInicial);
                break;
        }

    }

    private void llenarEnSentidoNE(int fil, int col, int posInicial){

        int contador = ordenCuadrado * ordenCuadrado;
        int n = posInicial;
        int i = fil;
        int j = col;

        int x = fil;
        int y = col;

        while(contador-->1){
            n++;
                i = i - 1;
                j = j + 1;

                if (i < 0) {
                    i = mCuadrado.length - 1;
                }
                if (j > mCuadrado.length - 1) {
                    j = 0;
                }
                if (mCuadrado[i][j] != 0) {
                    if (posicionPerimetral.equals(PRIMERA_FILA)) {
                        i = x + 1;
                        j = y;
                        mCuadrado[i][y] = n;
                    } else if (posicionPerimetral.equals(ULTIMA_COLUMNA)) {
                        i = x;
                        j = y - 1;
                        mCuadrado[i][j] = n;
                    }
                } else {
                    x = i;
                    y = j;
                    mCuadrado[i][j] = n;
                }
        }

    }

    private void llenarEnSentidoNO(int fil, int col, int posInicial) {

        int contador = ordenCuadrado * ordenCuadrado;
        int n = posInicial;
        int i = fil;
        int j = col;

        int x = fil;
        int y = col;

        while(contador-->1) {
            n++;

            i = i - 1;
            j = j - 1;

            if (i < 0) {
                i = mCuadrado.length - 1;
            }
            if (j < 0) {
                j = mCuadrado[0].length - 1;
            }

            if (mCuadrado[i][j] != 0) {
                if (posicionPerimetral.equals(PRIMERA_FILA)) {
                    i = x + 1;
                    j = y;
                    mCuadrado[i][y] = n;
                } else if (posicionPerimetral.equals(PRIMERA_COLUMNA)) {
                    i = x;
                    j = y + 1;
                    mCuadrado[i][j] = n;
                }
            } else {
                x = i;
                y = j;
                mCuadrado[i][j] = n;
            }
        }
    }

    private void llenarEnSentidoSE(int fil, int col, int posInicial) {

        int contador = ordenCuadrado * ordenCuadrado;
        int n = posInicial;
        int i = fil;
        int j = col;

        int x = fil;
        int y = col;

        while(contador-->1) {
            n++;

            i = i + 1;
            j = j + 1;

            if (i > mCuadrado.length - 1) {
                i = 0;
            }
            if (j > mCuadrado[0].length - 1) {
                j = 0;
            }

            if (mCuadrado[i][j] != 0) {
                if (posicionPerimetral.equals(ULTIMA_FILA)) {
                    i = x - 1;
                    j = y;
                    mCuadrado[i][j] = n;
                } else if (posicionPerimetral.equals(ULTIMA_COLUMNA)) {
                    i = x;
                    j = y - 1;
                    mCuadrado[i][j] = n;
                }
            } else {
                x = i;
                y = j;
                mCuadrado[i][j] = n;
            }
        }
    }

    private void llenarEnSentidoSO(int fil, int col, int posInicial) {

        int contador = ordenCuadrado * ordenCuadrado;
        int n = posInicial;
        int i = fil;
        int j = col;

        int x = fil;
        int y = col;

        while(contador-->1) {
            n++;

            i = i + 1;
            j = j - 1;

            if (i > mCuadrado.length - 1) {
                i = 0;
            }
            if (j < 0) {
                j = mCuadrado[0].length - 1;
            }
            if (mCuadrado[i][j] != 0) {
                if (posicionPerimetral.equals(ULTIMA_FILA)) {
                    i = x - 1;
                    j = y;
                    mCuadrado[i][j] = n;
                } else if (posicionPerimetral.equals(PRIMERA_COLUMNA)) {
                    i = x;
                    j = y + 1;
                    mCuadrado[i][j] = n;
                }
            } else {
                x = i;
                y = j;
                mCuadrado[i][j] = n;
            }
        }
    }

    public void calcularConstanteMagica() {
        constanteMagica = (ordenCuadrado*((ordenCuadrado*ordenCuadrado)+1))/2;
    }

    public int getOrdenCuadrado() {
        return ordenCuadrado;
    }

    public void setOrdenCuadrado(int ordenCuadrado) {
        this.ordenCuadrado = ordenCuadrado;
    }

    public String getPosicionPerimetral() {
        return posicionPerimetral;
    }

    public void setPosicionPerimetral(String posicionPerimetral) {
        this.posicionPerimetral = posicionPerimetral;
    }

    public String getSentidoLlenado() {
        return sentidoLlenado;
    }

    public void setSentidoLlenado(String sentidoLlenado) {
        this.sentidoLlenado = sentidoLlenado;
    }

    public int[][] getMCuadrado() {
        return mCuadrado;
    }

    public void setMCuadrado(int[][] mCuadrado) {
        this.mCuadrado = mCuadrado;
    }

    public int getConstanteMagica() {
        return constanteMagica;
    }

    public void setConstanteMagica(int constanteMagica) {
        this.constanteMagica = constanteMagica;
    }
}
