package com.example.bgomega;

import android.app.Application;

/**
 * グローバル変数を扱うクラス
 * Created by XXXXX on 20XX/XX/XX.
 */
public class common extends Application{
    // グローバルに扱う変数
    int x;        // 画面遷移した回数
    String from;    // 遷移元の画面名称

    /**
     * 変数を初期化する
     */
    public void init(){
        x = 0;
        from = "";
    }
}


