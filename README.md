# Paginated RecyclerView
[![](https://jitpack.io/v/softrunapp/Paginated-RecyclerView.svg)](https://jitpack.io/#softrunapp/Paginated-RecyclerView)


Android Paginated Recycler view Library

## Preview

<div>
<img src="screenshot_1.png" alt="preview" height="380">
<img src="screenshot_2.png" alt="preview" height="380">
<img src="screenshot_3.png" alt="preview" height="380">
</dive>


## Gradle

Add it in your root build.gradle at the end of repositories:


    allprojects {
      repositories {
          ...
          maven { url 'https://jitpack.io' }
       }
	  }
Add the dependency:

	dependencies {
	    implementation 'com.github.softrunapp:Paginated-RecyclerView:1.0.0'
	}




## Usage
Using Paginated RecyclerView is really simple.

1. Create adapter class extends from PaginatedAdapter and set your model and your view holder: 

```java
    public class MyAdapter extends PaginatedAdapter<User, MyAdapter.ViewHolder> {
      ...
      @Override
      public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
          getItem(position); //get item whit position
      }
      ...
```

2. Set items to your adapter in your Activity: 

```java
    MyAdapter adapter = new MyAdapter();
    adapter.setDefaultRecyclerView(this, R.id.recyclerView); //set recyclerview with id and Activity. by default set LinearLayout for LayoutManager and setFixSize to true
    adapter.setOnPaginationListener(new PaginatedAdapter.OnPaginationListener() {
              @Override
              public void onCurrentPage(int page) {
                  //current page which loaded in list
              }

              @Override
              public void onNextPage(int page) {
                  // call your method to get next page
              }

              @Override
              public void onFinish() {
                  // end of the list and all pages loaded
              }
          });
```

3. Get first page of your data

```java
    adapter.submitItems(yourListData);
```

## Customization
most of them are self-explaining

```java
    MyAdapter adapter = new MyAdapter();
    //setters
    adapter.setStartPage(1); //set first page of data. default value is 1.
    adapter.setPageSize(10); //set page data size. default value is 10.
    adapter.setRecyclerView(recyclerView); // set your RecyclerView with options
    
    //getters
    adapter.getStartPage(); // return start page
    adapter.getCurrentPage(); // return last page which loaded
    adapter.getRecyclerView(); // return recycler view 
```
## Example

## License
