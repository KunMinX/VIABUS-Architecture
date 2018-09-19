# 什么是 ViaBus
⭐ viabus 是一款响应式架构，借助总线完成数据的请求和响应，实现ui、业务的完全解耦。


# 为什么选择 ViaBus
- 该架构明确界定了什么是 ui、什么是业务，为代码解耦提供了现实依据。💡
- 从前改需求，需倒腾 2小时，现加上单元测试，也仅需 30分钟。🔥
- 从前项目重构，需 3个人连续加班 1个月，现只需 1个人每天抽 2小时 渐进式重构。💧
- 从前临时改需求，ui、presenter编写者 都得加班，现在互不拖累，独立自治，专注深耕各自领域。🌲
- 从前跨 Activity、跨组件实时双向通信，只能靠第三方库，现通过原生接口即可完成。⚡
- 从前使用 MVP、使用 Clean，导致类爆炸，现在你写的每个类都被充分利用。📌
- ...


# 如何使用 ViaBus
0. 在模块的 build.gradle 添加如下依赖
```
implementation "com.kunminx.viabus:viabus-android:0.2.9"
```

**1. 定义用于发送请求的接口**。接口继承于 IRequest，如：
```
public interface INoteRequest extends IRequest{
    void queryList();
    void insert(NoteBean bean);
    ...
}
```

**2. 定义 bus**，来支持请求接口的访问。如：
```
public class NoteBus extends BaseBus {
    public static INoteRequest note() {
        return (INoteRequest) getRequest(INoteRequest.class);
    }
    ...
}
```

**3. 在 ui 中注册成为响应接收者**。通过 bus 发送数据请求。在响应回调中，依据响应码实现 ui 逻辑的处理。
```
public class NoteListFragment extends Fragment implements IResponse {
    @Override
    public View onCreateView(LayoutInflater inf, ViewGroup cont, Bundle bundle) {
        //注册成为响应接收者
	NoteBus.registerResponseObserver(this);
        ... //发送数据请求
	NoteBus.note().queryList();
    }

    @Override //依据响应码处理 ui 逻辑
    public void onResult(Result testResult) {
        int resultCode = testResult.getResultCode();
        switch (resultCode) {
            case NoteResultCode.QUERY_LIST:
                beanList = (List<NoteBean>) testResult.getResultObject();
                mAdapter.refresh(beanList);
                ...
		
    @Override
    public void onDestroy() {
        ... //解除注册
        NoteBus.unregisterResponseObserver(this);
    }
}
```

**4. 在模块管理类中，将业务注册成为请求处理者**。
```
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        NoteBusiness noteBusiness = new NoteBusiness();
        noteBusiness.init(getApplicationContext());
        NoteBus.registerRequestHandler(noteBusiness);
        ...
    }
```


**5. 业务继承于 BaseBusiness**，异步处理各种请求，发送进度消息，或返回结果数据。
```
public class NoteBusiness extends BaseBusiness<NoteBus> implements INoteRequest {
    @Override
    public void queryList() {
        handleRequest((e) -> {
            List<NoteBean> list = mDataBase.getList(null, null);
	    ...
	    sendMessage(e, new Result(NoteResultCode.PROGRESS, bean.getId()));
	    ...
            return new Result(NoteResultCode.QUERY_LIST, list); 
        });
    }

    @Override
    public void insert(NoteBean bean) {
        handleRequest((e) -> { ... });
    }
    ...
```


# 见证者成就榜：

|见证者|重构耗时|项目链接|
|:--:|:--:|:--:|
|KunMinX|3小时|![image]()|
|MyateJx|3小时|![image]()|
|KunMinX|3小时|![image]()|
|KunMinX|3小时|![image]()|
