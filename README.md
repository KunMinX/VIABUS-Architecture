# android-viabus-architecture

# 什么是 viabus
⭐ viabus 是一款响应式架构，借助总线完成数据的请求和响应，实现ui、业务的完全解耦。

# 为什么选择 viabus
- 该架构明确界定了什么是 ui、什么是业务，为代码解耦提供现实依据。💡
- 从前改需求，需倒腾 2小时，现加上单元测试，也仅需 30分钟。🔥
- 从前项目重构，需 5个人 1个月，现只需 1个人每天抽 2小时 渐进式重构。💧
- 从前临时改需求，ui、presenter编写者 都得加班，现在互不拖累，独立自治，专注深耕各自领域。🌲
- 从前跨 Activity、跨组件实时双向通信，只能靠第三方库，现通过原生接口即可完成。⚡

# 如何使用 viabus
1.在模块的 build.gradle 添加如下依赖
```
implementation "com.kunminx.viabus:viabus-android:0.2.9"
```

2.定义数据请求接口，继承于 IRequest。例如：
```
public interface INoteRequest extends IRequest{

    void queryList();

    void insert(NoteBean bean);
    
    ...
}
```

3.定义 bus，来支持请求接口的访问。例如：
```
public class NoteBus extends BaseBus {

    public static INoteRequest note() {
        return (INoteRequest) getRequest(INoteRequest.class);
    }
    
    ...
}
```

4.在 ui 中注册成为响应接收者，通过 bus 发送数据请求，在响应回调中依据响应码实现 ui 逻辑的处理。
```
public class NoteListFragment extends Fragment implements IResponse {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        NoteBus.registerResponseObserver(this);
        ...
    }

    ...

    private void initViews() {
        ...
        
        //发送数据请求
        NoteBus.note().queryList();
    }

    @Override
    public void onResult(Result testResult) {
        //依据响应码实现 ui 逻辑的处理
        int resultCode = testResult.getResultCode();
        switch (resultCode) {
            case NoteResultCode.QUERY_LIST:
                List<NoteBean> beanList;
                if (testResult.getResultObject() != null) {
                    beanList = (List<NoteBean>) testResult.getResultObject();
                    mAdapter.setList(beanList);
                    mAdapter.notifyDataSetChanged();
                }
                break;
            case NoteResultCode.FAILURE:
                ...
                break;
            default:
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        NoteBus.unregisterResponseObserver(this);
    }
}
```

5.在模块管理类中，将业务注册成为请求处理者。
```
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        NoteBusiness noteBusiness = new NoteBusiness();
        noteBusiness.init(getApplicationContext());
        NoteBus.registerRequestHandler(noteBusiness);
        ...
    }
}
```


6.业务继承于 BaseBusiness，异步处理各种请求，并返回进度或结果数据于主线程中。
```
public class NoteBusiness extends BaseBusiness<NoteBus> implements INoteRequest {

	private DataBaseAdapter mDataBase;

    public void init(Context context) {
        mDataBase = new DataBaseAdapter();
        mDataBase.init(context);
    }

    @Override
    public void queryList() {
        handleRequest(new IAsync() {
            @Override
            public Result onExecute(ObservableEmitter<Result> e) throws IOException {
                List<NoteBean> list = mDataBase.getList(null, null);
                for (NoteBean bean : list) {
                    //进度的发送，背后是在主线程完成回调
                    sendMessage(e, new Result(NoteResultCode.PROGRESS, bean.getId()));
                }
                //结果数据的返回，背后是在主线程完成回调
                return new Result(NoteResultCode.QUERY_LIST, list);
            }
        });
    }

    @Override
    public void insert(NoteBean bean) {
        handleRequest(new IAsync() {
            @Override
            public Result onExecute(ObservableEmitter<Result> e) throws IOException {
                ...
            }
        });
    }

	...
}
```


# 见证者成就榜：

|见证者|重构耗时|项目链接|
|:--:|:--:|:--:|
|KunMinX|3小时|![image]()|
|MyateJx|3小时|![image]()|
|KunMinX|3小时|![image]()|
|KunMinX|3小时|![image]()|
