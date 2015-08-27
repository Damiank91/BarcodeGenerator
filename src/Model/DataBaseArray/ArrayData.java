package Model.DataBaseArray;

import java.util.ArrayList;


/**
 * Created by Damian on 2015-08-22.
 */
public class ArrayData {

    public ArrayList<BufferWithExcelConstructor> getArrayList;

    public ArrayData(){

        getArrayList = new ArrayList<>();
    }

    public void getNewBufforExToEx(BufferWithExcelConstructor bufferWithExcelConstructor){
        getArrayList.add(bufferWithExcelConstructor);
    }

    public int getSize(){
        return getArrayList.size();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(int i=0; i < getArrayList.size(); i++){
            builder.append(getArrayList.get(i));
            builder.append("\n");
        }
        return builder.toString();
    }

    public void remove(){
        getArrayList.clear();
    }
}
