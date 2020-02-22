# LittleNote
  第一个小项目，可初步实现编辑日志+阅读功能。还不成熟，功能尚未健全。
  以下是每个活动实现的具体介绍：
  
 1.StartActivity:以CoordinatorLayout为主布局，TabLayout + ViewPager + Fragment实现多页滑动。
   通过点击 FloatingActionButton 使用显式Intent切换到MainActivity.
  
  
 2.MainActivity:以DrawerLayout为主布局，NavigationView 和 FrameLayout 作为其直接子布局，实现侧滑栏。
   NavigationView 是滑动菜单栏中的内容，分为head_layout和nav_menu 上下两部分。
   head部分包括CircleImageView、两个 EditView分别用来显示用户头像、昵称和个性签名，可编辑。
   menu部分有四个菜单项，使用显示Intent分别跳转到响应的活动。
  
   FrameLayout 是主屏幕中显示的内容。包括AppBarLayout、ViewPager、FloatingActionBar三个控件。
   AppBarLayout内置ToolBar作为标题栏。ViewPager内置TabLayout实现内置多页滑动以展示图片。
   
   
 3.WriteActivity:以RelativeLayout为主布局，AppBarLayout、两个EditView、FrameLayout为直接子控件。
   AppBarLayout内置ToolBar+memu("保存"和"分享")作为标题栏，点击左上角的导航按钮，回到MAinActivity；
   点击“保存”，使用Toast弹出对话框，点击"分享"，使用ProgressDialog显示加载情况。
   两个EditView用于编辑日志。
   FrameLayout嵌套LinearLayout（内置三个ImageButton作为底部菜单栏），ImageView（用于显示拍照或相册
   中选择的照片）、TextView(用于显示当前时间和地点)。
   点击第一个ImageButton可显示当前时间；点击中间的ImageButton可调用相机或相册，点击右边的ImageButton
   可调用百度地图显示具体位置。
    
    
 4.ReadActivity:LinearLayout为主布局，Toolbar、SwipeRefreshLayout为其直接子控件。
   SwipeRefreshLayout + RecyclerView 实现下拉刷新。设置RecyclerView的点击事件，可查看每个item的
   详细情况。
     
     
 5.SubReadActivity:coordinatorlayout为主布局，AppBarLayout、NestedScrollView为直接子控件。
   NestedScrollView+CardView+WebView实现网络请求并加载网页。
   AppBarLayout + CollapsingToolbarLayout + Toolbar实现折叠是标题。
   
   
 6.ShelActivity:LinearLayout布局，Toolbar、ListView为子控件。运用JSON解析数据。
 
