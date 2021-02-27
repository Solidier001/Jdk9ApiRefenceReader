package com.example.myapplication.Utils;

import com.example.myapplication.domain.ClassesItem;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ClassesItems extends ArrayList<ClassesItem> {
    @Override
    public boolean remove(@NotNull Object o) {
        return super.remove(o);
    }

    @Override
    public int indexOf(Object o) {
        int i=0;
        ClassesItem elementData=(ClassesItem)o;
        if (elementData == null) {
            i++;
            for ( ClassesItem element : this){
                if (element==null)
                    return i;
            }
        } else {
            for ( ClassesItem element : this){
                i++;
                    if (elementData.getTittle().equals(element.getTittle().toString()))
                        return i;
            }
        }
        return -1;
    }
}
