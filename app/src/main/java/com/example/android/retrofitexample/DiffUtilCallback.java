package com.example.android.retrofitexample;

import android.support.v7.util.DiffUtil;

import com.example.android.retrofitexample.model.Weather;

import java.util.List;

public class DiffUtilCallback extends DiffUtil.Callback {
    private final List<Weather> oldList;
    private final List<Weather> newList;

    public DiffUtilCallback(List<Weather> oldList, List<Weather> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int i, int i1) {
        return oldList.get(i).getEpoch() == newList.get(i1).getEpoch();
    }

    @Override
    public boolean areContentsTheSame(int i, int i1) {
        return oldList.get(i).equals(newList.get(i1));
    }
}
