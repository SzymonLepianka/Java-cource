public class Matrix {
    double[]data;
    int rows;
    int cols;
    //...
    Matrix(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        data = new double[rows*cols];
    }
    Matrix(double[][] d){
        int max = d[0].length;
        for(int i=0;i<d.length;i++){
            if(d[i].length > max){
                max = d[i].length;
            }
        }
        //System.out.println(max);
        this.rows = d.length;
        this.cols = max;
        this.data = new double[max*d.length];
        for(int i=0;i<d.length;i++){
            int j=0;
            while (j<d[i].length){
                this.data[max*i+j]=d[i][j];
                j++;
            }
            while(j<max){
                this.data[max*i+j]=0;
                j++;
            }
        }
    }



    double[][] asArray(){
        double[][] result = new double[this.rows][this.cols];
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                result[i][j]=this.data[i*this.rows+j];
            }
        }
        return result;
    }


    double get(int r,int c){
        return this.data[r*this.cols+c];
    }
    void set (int r,int c, double value){
        this.data[this.cols*r+c] = value;
    }

    public String toString(){
        StringBuilder buf = new StringBuilder();
        buf.append("[");
        for(int i=0;i<this.rows;i++){
            buf.append("[");
            for (int j = 0; j < this.cols; j++) {
                buf.append(this.data[i*this.rows+j]);
                if (j==this.cols - 1){
                    buf.append("]");
                }
                else{
                    buf.append(", ");
                }
            }
        }
        return buf.toString();
    }

    void reshape(int newRows,int newCols){
        if(rows*cols != newRows*newCols)
            throw new RuntimeException(String.format("%d x %d matrix can't be reshaped to %d x %d",rows,cols,newRows,newCols));
        this.rows=newRows;
        this.cols=newCols;
    }

    int[] shape(){
        return new int []{this.rows, this.cols};
    }

    Matrix add(Matrix m){
        Matrix added = new Matrix(this.rows, this.cols);
        if(this.rows == m.rows && this.cols==m.cols){
            for (int i = 0; i < this.rows; i++) {
                for (int j = 0; j < this.cols; j++) {
                    added.set(i,j,this.get(i,j)+m.get(i,j));
                }
            }
        }else {
            throw new RuntimeException(String.format("matrix can't be added"));
        }
        return added;
    }
    //2.8 zapytać się bartka jak to zrobić, bez 2.10
}

