package com.company;

public class CustomStack<T> {

    protected int size;
    protected final Object[] Arr;
    protected int top;

    public CustomStack(int maxSize){
        this.size = maxSize;
        this.Arr = new Object[size];
        this.top = -1;
    }

    public void push(T item) {
        try {
            if (top + 1 == Arr.length)
                throw new Exception("Stack is full");
        }
        catch(Exception ex){ System.out.println(ex.getMessage());}

        Arr[++top] = item;

    }

    public T pop() {
        try {
            if (top == Arr.length)
                throw new Exception("Stack is empty");
        }
        catch(Exception ex){ System.out.println(ex.getMessage());}


        return (T)Arr[top--];
    }

    public T peek() throws Exception{

            if (top == -1)
                throw new Exception("Stack is empty");

            return (T)Arr[top];

    }

    public boolean isEmpty(){
        if (top == -1)
            return true;

        return false;
    }

    public int search(T item){

        for(int i = 0; i < top; i++){
            if(Arr[i] == item){
                return i;
            }
        }
        return -1;
    }

    public int getSize(){
        return this.size;
    }

}
