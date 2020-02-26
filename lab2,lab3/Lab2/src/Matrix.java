public class Matrix {
    double[] data;
    int rows;
    int cols;

    Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        data = new double[rows * cols];
    }

    //2.1
    Matrix(double[][] d) {
        int max = d[0].length;
        for (int i = 0; i < d.length; i++) {
            if (d[i].length > max) {
                max = d[i].length;
            }
        }
        this.rows = d.length;
        this.cols = max;
        this.data = new double[max * d.length];
        for (int i = 0; i < d.length; i++) {
            int j = 0;
            while (j < d[i].length) {
                this.data[max * i + j] = d[i][j];
                j++;
            }
            while (j < max) {
                this.data[max * i + j] = 0;
                j++;
            }
        }
        /*for(int j=0;j<rows; j++ ){
            System.arraycopy(d[j],0,data,j*cols,d[j].length);
        }*/
    }
    /*public Matrix(double[][] data) {
        int maxRowLength = 0;

        for (double[] row : data) {
            maxRowLength = Math.max(maxRowLength, row.length);
        }


        this.rows = data.length;
        this.cols = maxRowLength;
        this.data = new double[data.length * maxRowLength];
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                try {
                    set(y, x, data[y][x]);
                } catch (IndexOutOfBoundsException ignored) {
                    break;
                }
            }
        }
    }*/
    //2.2
    double[][] asArray() {
        double[][] result = new double[this.rows][this.cols];
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                result[i][j] = get(i,j);
            }
        }
        return result;
    }

    //2.3
    double get(int r, int c) {
        return this.data[r * this.cols + c];
    }
    void set(int r, int c, double value) {
        this.data[this.cols * r + c] = value;
    }
    double getRows(){
        return rows;
    }
    double getCols(){
        return cols;
    }

    //2.4
    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append("[");
        for (int i = 0; i < this.rows; i++) {
            buf.append("[");
            for (int j = 0; j < this.cols; j++) {
                buf.append(get(i,j));
                //buf.append(this.data[i * this.rows + j]);
                if (j == this.cols - 1) {
                    buf.append("]");
                } else {
                    buf.append(", ");
                }
            }
        }
        buf.append("]");
        return buf.toString();
    }

    //2.5
    void reshape(int newRows, int newCols) {
        if (rows * cols != newRows * newCols)
            throw new RuntimeException(String.format("%d x %d matrix can't be reshaped to %d x %d", rows, cols, newRows, newCols));
        this.rows = newRows;
        this.cols = newCols;
    }

    //2.6
    int[] shape() {
        return new int[]{this.rows, this.cols};
    }

    //2.7
    Matrix add(Matrix m) {
        Matrix added = new Matrix(this.rows, this.cols);
        if (this.rows == m.rows && this.cols == m.cols) {
            for (int i = 0; i < this.rows; i++) {
                for (int j = 0; j < this.cols; j++) {
                    added.set(i, j, this.get(i, j) + m.get(i, j));
                }
            }
        } else {
            throw new ArithmeticException("matrix can't be added");
        }
        return added;
    }

    //2.8
    Matrix sub(Matrix m) {
        Matrix subtracted = new Matrix(this.rows, this.cols);
        if (this.rows == m.rows && this.cols == m.cols) {
            for (int i = 0; i < this.rows; i++) {
                for (int j = 0; j < this.cols; j++) {
                    subtracted.set(i, j, this.get(i, j) - m.get(i, j));
                }
            }
        } else {
            throw new RuntimeException("matrix can't be subtracted");
        }
        return subtracted;
    }
    Matrix mul(Matrix m) {
        Matrix multiplied = new Matrix(this.rows, this.cols);
        if (this.rows == m.rows && this.cols == m.cols) {
            for (int i = 0; i < this.rows; i++) {
                for (int j = 0; j < this.cols; j++) {
                    multiplied.set(i, j, this.get(i, j) * m.get(i, j));
                }
            }
        } else {
            throw new RuntimeException("matrix can't be multiplied");
        }
        return multiplied;
    }
    Matrix div(Matrix m) {
        Matrix divided = new Matrix(this.rows, this.cols);
        if (this.rows == m.rows && this.cols == m.cols) {
            for (int i = 0; i < this.rows; i++) {
                for (int j = 0; j < this.cols; j++) {
                    if (m.get(i,j)==0){
                        divided.set(i, j,0);
                    }else{
                        divided.set(i, j, this.get(i, j) / m.get(i, j));
                    }
                }
            }
        } else {
            throw new RuntimeException("matrix can't be subtracted");
        }
        return divided;
    }
    Matrix add(double w) {
        Matrix added = new Matrix(this.rows, this.cols);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                added.set(i, j, this.get(i, j) + w);
            }
        }
        return added;
    }
    Matrix sub(double w) {
        Matrix subtracted = new Matrix(this.rows, this.cols);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                subtracted.set(i, j, this.get(i, j) - w);
            }
        }
        return subtracted;
    }
    Matrix mul(double w) {
        Matrix multiplied = new Matrix(this.rows, this.cols);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                multiplied.set(i, j, this.get(i, j) * w);
            }
        }
        return multiplied;
    }
    Matrix div(double w) {
        Matrix divided = new Matrix(this.rows, this.cols);
        if (w != 0) {
            for (int i = 0; i < this.rows; i++) {
                for (int j = 0; j < this.cols; j++) {

                        divided.set(i, j, this.get(i, j) / w);

                }
            }
        } else {
            throw new RuntimeException("matrix can't be subtracted");
        }
        return divided;
    }
    //2.9
    Matrix dot(Matrix m) {
        Matrix multiplied = new Matrix(this.rows, m.cols);
        if (this.cols == m.rows) {
            for (int i = 0; i < this.rows; i++) {
                for (int j = 0; j < m.cols; j++) {
                    double suma = 0;
                    for (int k = 0; k < this.cols; k++) {
                        suma = suma + this.get(i, k) * m.get(k, j);
                        //multiplied.setAdd(i, j, this.get(i,k) * m.get(k, j));
                        //data[i*cols + j] +=this.get(i,k) * m.get(k, j);
                    }
                    multiplied.set(i, j, suma);
                }
            }
            return multiplied;
        } else {
            throw new ArithmeticException("matrix can't be multiplied(dot)");
        }

    }
    //2.10
    double frobenius(){
        double x=0;
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                x = x + get(i,j)*get(i,j);
            }
        }
        return Math.sqrt(x);
    }

    //KARTKÓWKA (kod na zajęciach nie zadziałał, ale był dobry :) miałem błąd w jednej z metod, który poprawiłem potem)
    Matrix getColumn(int i){
        if (i > 0 || i < this.rows) {
            //double[][] d = new double [this.rows][i+1];
            //Matrix m = new Matrix(d);
            Matrix m = new Matrix(this.rows,1);
            //System.out.println(this.cols);
            for (int j = 0; j < this.rows; j++) {
                m.set(j,0, this.get(j, i));
            }
            return m;
        } else {
                throw new RuntimeException("BŁĄD");
            }
    }
}

