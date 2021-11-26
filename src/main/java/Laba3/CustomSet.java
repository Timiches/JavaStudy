package com.company;

public class CustomSet extends CustomStack<Integer> {

    CustomSet(int capacity){
       super(capacity);
    }

    @Override
    public void push(Integer item){

        try {
            if (top + 1 == Arr.length)
                throw new Exception("Set is full");
        }
        catch(Exception ex){ System.out.println(ex.getMessage());}


        if(this.search(item) != -1)
            return;

        Arr[++top] = item;
    }

    public CustomSet intersection(CustomSet set){
        CustomSet result = new CustomSet(this.size + set.size);

        for (int i = 0; i < this.size; i++)
        {
            for (int j = 0; j < set.size; j++){
             if(this.Arr[i] == set.Arr[j]){
                 result.push((Integer) Arr[i]);
             }

            }
        }

        return result;
    }

    public CustomSet union(CustomSet set){
       CustomSet result = new CustomSet(this.size + set.size);

       for (int i = 0; i < this.size; i++) {
                   result.push((Integer) Arr[i]);
       }
        for (int j = 0; j < set.size; j++){
                result.push((Integer) set.Arr[j]);
        }

       return result;
    }

}
