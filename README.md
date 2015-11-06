# NeverEmptyListView
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-NeverEmptyListView-green.svg?style=true)](https://android-arsenal.com/details/1/2734)

This custom listview basically shows a holder view when it's adapter is empty. For example, you may fetch list datas from network and refresh your adapter with coming data. If the data is empty, your listview shows a huge blank space. With this custom listview, you can show a holder view instead of blank space and your design looks prettier.(Your minimum sdk should be 15 and higher)

![alt tag](https://github.com/mhmtsdtkrt/NeverEmptyListView/blob/master/demo/src/main/res/preview/preview.png?raw=true)

## Usage

1)First of all, add below in your **build.gradle** at the end of repositories:
    
    repositories {
        // ...
        maven { url "https://jitpack.io" }
    }
    
Add below dependency also.

    compile 'com.github.mskurt:NeverEmptyListView:v1.0.1' 

2)Add the view to your layout xml. You must use features which are belong to NeverEmptyListView with `custom` namespace. It is recommended to use **square size image** for **holderImageBackground**. 

    <RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
        android:layout_width="match_parent"
        android:layout_height="match_parent">
    
        <net.mskurt.neveremptylistviewlibrary.NeverEmptyListView
            xmlns:custom="http://schemas.android.com/apk/res-auto"
            android:id="@+id/listview"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            custom:holderTextTopMargin="20dp"
            custom:holderBackgroundColor="#ffffff"
            custom:holderTextFontSize="9sp"
            custom:holderTextColor="#000000"
            custom:holderText="This is holder text."
            custom:holderImageBackground="@drawable/an_example_image"/>

    </RelativeLayout>
    
3)Now you can set an adapter which has no item to your NeverEmptyListView. 

``` java
//Create an empty adapter
String[] values={};
ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, android.R.id.text1, values);

//Set NeverEmptyListView's adapter
NeverEmptyListView neverEmptyListView=(NeverEmptyListView)findViewById(R.id.listview);
neverEmptyListView.setAdapter(adapter);
```

4)You can set a click listener for holder to do anything.

``` java
neverEmptyListView.setHolderClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        //Do anything here
    }
});
```
 
5)If you want to use notifyDataSetChange method of adapter, you should do it like below.

``` java
neverEmptyListView.notifyDataSetChanged(YOUR_ADAPTER);
```

6)If you need the listview at runtime (NeverEmptyListView is a custom view which contains a listview), you can get it like below.

``` java
neverEmptyListView.getListview();
```
For example, you can get the listview and setOnItemClickListener to it.

``` java
neverEmptyListView.getListview().setOnItemClickListener(new AdapterView.OnItemClickListener() {
     @Override
     public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                
    }
});
```

## Holder View Customizing
Holder view is mostly customizable. You can use below feature list.

![alt tag](https://github.com/mhmtsdtkrt/NeverEmptyListView/blob/master/demo/src/main/res/preview/holder_features.png?raw=true)

    holderImageBackground : color or drawable reference
    holderImageSize : dimension in dp or px
    holderTextRightLeftMargin : dimension in dp or px
    holderTextTopMargin : dimension in dp or px
    holderTextFontSize : dimension in sp
    holderText : harcoded string or string reference
    holderTextColor : hardcoded color or color reference
    holderBackgroundColor : hardcoded color or color reference
    
You can change these features at runtime

    setHolderImageBackground(int drawableId)
    setHolderImageSize(int sizePixel)
    getHolderImageSize()
    setHolderText(String text)
    getHolderText()
    setHolderTextColor(int color)
    setHolderTextFontSize(int unit,float size)
    setHolderTextTypeFace(Typeface typeFace)
    setHolderTextLeftRightMargin(int marginPixel)
    setHolderTextTopMargin(int marginPixel)
    setHolderBackgroundColor(int color)

## Warning
If you want to use listview's basic attributes in xml, you should use it with **custom** namespace like below.

    custom:listviewDivider
    custom:listviewDividerHeight
    custom:listviewFooterDividersEnabled
    custom:listviewHeaderDividersEnabled
    custom:listviewCacheColorHint
    custom:listviewDrawSelectorOnTop
    custom:listviewScrollingCache
    custom:listviewFastScrollEnabled
    custom:listviewChoiceMode
    custom:listviewListSelector
    custom:listviewTextFilterEnabled
    custom:listviewSmoothScrollbar
    custom:listviewStackFromBottom
    custom:listviewTranscriptMode

## Developed By
Sedat Kurt - www.mskurt.net - sedat@4pps.co

    Copyright 2015 Sedat Kurt.
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
       http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

