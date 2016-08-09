package DataStructures.Arrays;

/**
 * Created by ketjow on 09.08.16.
 */
public class KetjowArray<T> {
    public int size;
    private Element<T> firstElement;

    private <T> KetjowArray(){
        this.size = 0;
    }


    private <T> KetjowArray(int size){
        this.size = size;
        createArray(size);
    }



    private class Element<T>{
        private  int index;
        private Element<T> element;
        private T data;

        public <T>Element(int index){
            this.index = index;
        }

        private Element<T> addNextElement(){
            element = new Element<T>(index + 1);
            return element;
        }

        public void setData(T data){
            this.data = data;
        }

        public T getData(){
            return data;
        }
    }


    private void createArray(int size){
        firstElement = new Element<T>(0);
        Element<T> currentElement = firstElement;
        for(int i=1; i<size; i++){
            currentElement = currentElement.addNextElement();
        }
    }

    private Element<T> getElementWithIndex(int index){
        if(index> this.size || index<0){
            throw new IndexOutOfBoundsException();
        }else{
            return recurForElement(firstElement,index);
        }
    }

    public T getValue(int index){
        Element<T> element = getElementWithIndex(index);
        return element.getData();
    }

    public void setValue(int index, T value){
        Element<T> element = getElementWithIndex(index);
        element.setData(value);
    }

    private Element<T> recurForElement(Element<T> element, int index) {
        Element<T> valueToReturn = null;
        if(element.index == index){
            valueToReturn = element;
        }else{
            element = element.element;
            valueToReturn = recurForElement(element, index);
        }
        return valueToReturn;
    }

    public static <T> KetjowArray<T> makeArray(int size){
        if(size == 0){
            return new KetjowArray<T>();
        }else{
            return new KetjowArray<T>(size);
        }
    }

    public static void main (String[] args){
        KetjowArray<String> array = KetjowArray.makeArray(3);
        array.setValue(2, "TestString");
        String valFromIndex2 = array.getValue(2);
        System.out.print(valFromIndex2);
    }
}
