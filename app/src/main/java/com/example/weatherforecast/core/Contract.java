package com.example.weatherforecast.core;

import android.content.Context;
import android.widget.TextView;

import com.example.weatherforecast.RetrofitExampleItem;
import com.example.weatherforecast.model.APIInterface;

import java.util.List;

public interface Contract {
    interface View {
        void onGetDataSuccessful(TextView tvPlace, List<RetrofitExampleItem> mRetrofitExampleList);
        void onGetDataFailure(TextView tvPlace);
    }
    interface Presenter {
        void getDataInApi(Context context);
    }
    interface Model {
        APIInterface initRetrofitCallApi();
    }
//    interface onGetDataInApi {
//        void onSuccessful(TextView tvPlace, List<RetrofitExampleItem> mRetrofitExampleList);
//        void onFailure(TextView tvPlace);
//    }
}
