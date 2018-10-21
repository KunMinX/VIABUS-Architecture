![image](https://github.com/KunMinX/android-viabus-architecture/blob/master/images/viabuslogo.png)

![image](https://img.shields.io/badge/jcenter-0.4.1-brightgreen.svg)
![image](https://img.shields.io/badge/api-%2B15-blue.svg)
![image](https://img.shields.io/badge/license-Apache2.0-blue.svg)
![image](https://img.shields.io/badge/author-KunMinX-orange.svg)
![image](https://img.shields.io/badge/qq群-905136432-orange.svg)

### [中文文档](https://github.com/KunMinX/android-viabus-architecture/blob/master/README_CN.md) | [English](https://github.com/KunMinX/android-viabus-architecture/blob/master/README_EN.md)

# 什么是 ViaBus
⭐ ViaBus 是一款响应式架构，借助总线转发数据的请求和响应，实现ui、业务的完全解耦。

![image](https://github.com/KunMinX/android-viabus-architecture/blob/master/images/viabus_flow.png)

# 为什么选择 ViaBus 
- 1分钟即可掌握，ViaBus 的结构与使用方式。💡
- 当下就可使用，对原项目架构完全兼容，即插即用。🔥
- 允许以 业务或模块为单位，每天1小时，完成渐进式重构。💧
- 职责边界明确，无论是写 UI 还是业务，不再被打断、相互不拖累。🌱
- 原生接口即可实现 跨 Activity、跨组件的 实时双向通信。⚡
- 无编译时注解，基于 Viabus 编写的组件可在任何项目中直接使用。🌎
- 更少的重复工作，扁平的交互模式，代码复用率提升至100%。💪
- ...

更多依据详见 [Wiki - Android：四大架构的优缺点，你真的了解吗？](https://github.com/KunMinX/android-viabus-architecture/wiki/Android-%E5%90%84%E7%B1%BB%E6%9E%B6%E6%9E%84%E6%A8%AA%E5%90%91%E6%AF%94%E5%AF%B9)

# 如何使用 ViaBus 
在模块的 build.gradle 添加如下依赖
```
implementation "com.kunminx.viabus:viabus-android:0.4.1"
```
使用方法详见 [Wiki - 1分钟掌握 ViaBus 架构和使用](https://github.com/KunMinX/android-viabus-architecture/wiki/1%E5%88%86%E9%92%9F%E6%8E%8C%E6%8F%A1-ViaBus-%E6%9E%B6%E6%9E%84%E7%9A%84%E4%BD%BF%E7%94%A8)

# 谁在用 Viabus

|见证者|Logo|作品地址或项目链接|
|:--:|:--:|:--:|
|[MyateJx](https://github.com/MyateJx)|![sakernote](https://github.com/KunMinX/android-viabus-architecture/blob/master/images/icon_sakernote.png)|[舒心录 - 酷安老牌记事本 app](https://www.coolapk.com/apk/com.myatejx.sakernote)|
|[MyateJx](https://github.com/MyateJx)|![gankio](https://github.com/KunMinX/android-viabus-architecture/blob/master/images/icon_gank.png)|[gank.io 客户端](https://github.com/MyateJx/GankIo-viabus-architecture)|


# 鸣谢

RxJava

# License

```
Copyright 2018 KunMinX

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
·
·
·
·
·
·
# 你还在等那个，手把手带你重构的人出现吗？

## 前言

本文的编写，前前后后筹备了两个礼拜。

以下你就可以看到，一位单枪匹马的帅哥，是如何以一己之力，重构整座“屎山”的。

这位帅哥一直在徘徊，本文到底该写给谁看？是只在乎写功能的码农吗？不了不了，码农若真的有心提升代码质量，就不会在项目中丧心病狂的堆积屎山。

![](https://github.com/KunMinX/android-viabus-architecture/blob/master/images/how_to_refact_project/ride_a_horse.webp)

于是干脆写写重构心得、分享重构思路，让那些有意识在这方面有所提升的帅哥美女们，少走弯路吧！

>在此首先感谢主管的信任与支持。本次重构中，帅哥在部门内部兜售并率先使用自主设计的 Viabus 架构，5 天内完成 60 个类的核心模块的重构。（不要慌，正如你所见到的，架构已在 GitHub 开源）

以下正文。

## 代码是如何越写越烂的？

你是否经常听同事自嘲，“开始还想好好写，不知怎滴，后面越写越烂”？

代码越写越烂，果真是个没有端倪、无法干预的魔咒玄学吗？

让我们来**快速浏览一下** 重构前 项目里的代码是怎么写的。

```
protected void initView() {
        PagerAdapter pagerAdapter = new PagerAdapter();
        viewPagerFix.setOffscreenPageLimit(4);
        viewPagerFix.setAdapter(pagerAdapter);
        mFragmentBinding.tabLayout.setTabData(pagerAdapter.titles);
        mFragmentBinding.tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPagerFix.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        viewPagerFix.addOnPageChangeListener(new ViewPagerFix.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                KeyboardUtils.hideSoftInput(getActivity());
            }

            @Override
            public void onPageSelected(int position) {
                mFragmentBinding.tabLayout.setCurrentTab(position);
                if (mViewModel.getXXXDetailTouchManager().isZZBG()) { 

                    zzbgPageSelected(position);

                } else if (mViewModel.getXXXDetailTouchManager().isYBJZ()) { 
                    switch (position) {
                        case 0:
                        case 1:
                            mViewModel.removeAllArrows();

                            if (mAttachmentFragment != null) {
                                mAttachmentFragment.hideClickHighLight(ALBUM_ALL);
                            }
                            break;
                        case 2:
                            if (mAttachmentFragment != null) {
                                mAttachmentFragment.initAttachTitle();
                            }
                            mViewModel.showAllArrows();

                            break;
                        default:
                            break;
                    }
                } else {
                    switch (position) {
                        case 0:
                        case 1:
                        case 2:
                            mViewModel.removeAllArrows();
                            //hideBottomLayout();
                            if (mAttachmentFragment != null) {
                                mAttachmentFragment.hideClickHighLight(ALBUM_ALL);
                            }
                            break;
                        case 3:
                            if (mAttachmentFragment != null) {
                                mAttachmentFragment.initAttachTitle();
                            }
                            mViewModel.showAllArrows();

                            break;
                        default:
                            break;
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPagerFix.setCurrentItem(0);
        mFragmentBinding.headContainer.getTitleView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mViewModel.getXXXDetailTouchManager().isZZBG()) { 
                    return;
                }

                mViewModel.changeWyhcrwMajorState(); 
                EventBus.getDefault().post(new RefreshItemEventBus(
                        mViewModel.getXXXDetailTouchManager().getCurrentWyhcrw()));
            }
        });
    }

    private void zzbgPageSelected(int position) {

        if (mScreenNum == 3) {

            switch (position) {
                case 0:
                case 1:
                    mViewModel.removeAllArrows();

                    if (mAttachmentFragment != null) {
                        mAttachmentFragment.hideClickHighLight(ALBUM_ALL);
                    }
                    break;
                case 2:
                    mViewModel.showAllArrows();

                    break;
                default:
                    break;
            }

        } else {

            switch (position) {
                case 0:
                    mViewModel.removeAllArrows();

                    if (mAttachmentFragment != null) {
                        mAttachmentFragment.hideClickHighLight(ALBUM_ALL);
                    }
                    break;
                case 1:
                    mViewModel.showAllArrows();

                    break;
                default:
                    break;
            }

        }
        ;

    }



    /**
     * viewPager适配器
     */
    private class PagerAdapter extends FragmentPagerAdapter {

        String[] titles;

        PagerAdapter() {
            super(getChildFragmentManager());
            if (mViewModel.getXXXDetailTouchManager().isZZBG()) {

                if (mScreenNum == 3) {

                    titles = getResources().getStringArray(R.array.XXX_detail_tabs_for_no_tbjt);

                } else {
                    titles = getResources().getStringArray(R.array.XXX_detail_tabs_for_zzbg);

                }

            } else if (mViewModel.getXXXDetailTouchManager().isYBJZ()) {
                titles = getResources().getStringArray(R.array.XXX_detail_tabs_for_ybjz);
            } else {
                titles = getResources().getStringArray(R.array.XXX_detail_tabs);
            }
        }

        @Override
        public Fragment getItem(int position) {
            if (mViewModel.getXXXDetailTouchManager().isZZBG()) {

                return zzbgGetItem(position);

            } else if (mViewModel.getXXXDetailTouchManager().isYBJZ()) {
                switch (position) {
                    case 0:
                        if (mXXXTuBanPicFragment == null) {
                            mXXXTuBanPicFragment = XXXTuBanPicFragment.newInstance(
                                    mViewModel.getUniqueCode(),
                                    mViewModel.getXXXTouchManger()
                            );
                        }
                        return mXXXTuBanPicFragment;
                    case 1:
                        if (mRecordFragment == null) {
                            mRecordFragment = XXXRecordFragment.newInstance(mViewModel.getXXXDetailTouchManager());
                        }
                        return mRecordFragment;

                    default:
                        if (mAttachmentFragment == null) {
                            mAttachmentFragment = XXXAttachmentFragment.newInstance(
                                    mViewModel.getAttachments(),
                                    mViewModel.getOriginalAttachments(),
                                    mViewModel.getUniqueCode(),
                                    mViewModel.getXXXTouchManger(),
                                    XXXDetailFragment.this
                            );
                        }
                        return mAttachmentFragment;
                }
            } else {
                switch (position) {
                    case 0:
                        if (mXXXTuBanPicFragment == null) {
                            mXXXTuBanPicFragment = XXXTuBanPicFragment.newInstance(
                                    mViewModel.getUniqueCode(),
                                    mViewModel.getXXXTouchManger()
                            );
                        }
                        return mXXXTuBanPicFragment;
                    case 1:
                        if (mAttributeFragment == null) {
                            mAttributeFragment = XXXAttributeFragment.newInstance(
                                    mViewModel.getUniqueCode(),
                                    mViewModel.getXXXTouchManger()
                            );
                        }
                        return mAttributeFragment;

                    case 2:
                        if (mRecordFragment == null) {
                            mRecordFragment = XXXRecordFragment.newInstance(mViewModel.getXXXDetailTouchManager());
                        }
                        return mRecordFragment;
                    default:
                        if (mAttachmentFragment == null) {
                            mAttachmentFragment = XXXAttachmentFragment.newInstance(
                                    mViewModel.getAttachments(),
                                    mViewModel.getOriginalAttachments(),
                                    mViewModel.getUniqueCode(),
                                    mViewModel.getXXXTouchManger(),
                                    XXXDetailFragment.this
                            );
                        }
                        return mAttachmentFragment;
                }
            }
        }

        private Fragment zzbgGetItem(int position) {

            if (mScreenNum == 3) {

                switch (position) {
                    case 0:
                        if (mAttributeFragment == null) {
                            mAttributeFragment = XXXAttributeFragment.newInstance(
                                    mViewModel.getUniqueCode(),
                                    mViewModel.getXXXTouchManger()
                            );
                        }
                        return mAttributeFragment;
                    case 1:
                        if (mRecordFragment == null) {
                            mRecordFragment = XXXRecordFragment.newInstance(
                                    mViewModel.getXXXDetailTouchManager());
                        }
                        return mRecordFragment;
                    default:
                        if (mAttachmentFragment == null) {
                            mAttachmentFragment = XXXAttachmentFragment.newInstance(
                                    mViewModel.getAttachments(),
                                    mViewModel.getOriginalAttachments(),
                                    mViewModel.getUniqueCode(),
                                    mViewModel.getXXXTouchManger(),
                                    XXXDetailFragment.this
                            );
                        }
                        return mAttachmentFragment;
                }

            } else {
                switch (position) {
                    case 0:
                        if (mRecordFragment == null) {
                            mRecordFragment = XXXRecordFragment.newInstance(
                                    mViewModel.getXXXDetailTouchManager());
                        }
                        return mRecordFragment;
                    default:
                        if (mAttachmentFragment == null) {
                            mAttachmentFragment = XXXAttachmentFragment.newInstance(
                                    mViewModel.getAttachments(),
                                    mViewModel.getOriginalAttachments(),
                                    mViewModel.getUniqueCode(),
                                    mViewModel.getXXXTouchManger(),
                                    XXXDetailFragment.this
                            );
                        }
                        return mAttachmentFragment;
                }
            }

        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Object object = super.instantiateItem(container, position);
            if (mViewModel.getXXXDetailTouchManager().isZZBG()) {
                if (mScreenNum == 3) {

                    switch (position) {
                        case 0:
                            mAttributeFragment = (XXXAttributeFragment) object;
                            break;
                        case 1:
                            mRecordFragment = (XXXRecordFragment) object;
                            break;
                        default:
                            mAttachmentFragment = (XXXAttachmentFragment) object;
                            break;
                    }

                } else {
                    switch (position) {
                        case 0:
                            mRecordFragment = (XXXRecordFragment) object;
                            break;
                        default:
                            mAttachmentFragment = (XXXAttachmentFragment) object;
                            break;
                    }
                }

                return object;
            } else if (mViewModel.getXXXDetailTouchManager().isYBJZ()) {
                switch (position) {
                    case 0:
                        mXXXTuBanPicFragment = (XXXTuBanPicFragment) object;
                        break;
                    case 1:
                        mRecordFragment = (XXXRecordFragment) object;
                        break;
                    default:
                        mAttachmentFragment = (XXXAttachmentFragment) object;
                        break;
                }
                return object;
            } else {
                switch (position) {
                    case 0:
                        mXXXTuBanPicFragment = (XXXTuBanPicFragment) object;
                        break;
                    case 1:
                        mAttributeFragment = (XXXAttributeFragment) object;
                        break;
                    case 2:
                        mRecordFragment = (XXXRecordFragment) object;
                        break;
                    default:
                        mAttachmentFragment = (XXXAttachmentFragment) object;
                        break;
                }
                return object;
            }
        }

        @Override
        public int getCount() {
            if (mViewModel != null) {
                if (mViewModel.getXXXDetailTouchManager().isZZBG()) {
                    if (mScreenNum == 3) {
                        return 3;
                    }
                    return 2;
                }
                if (mViewModel.getXXXDetailTouchManager().isYBJZ()) {
                    return 3;
                } else {
                    return 4;
                }
            }
            return 0;
        }

    }

 
```

（为保护隐私，模块类名已替换为“XXX”）

可以看到，该主页目前服务于 3 个地区，每个地区对子页面的展示都有定制需求。

if else switch if else switch，只在乎功能实现的码农就是这么写的。

一个地区 50 行，那要是 10 个地区呢？公司领导放话要支持全国 100 个乡镇地区！那 100 个地区呢？？？

![](https://github.com/KunMinX/android-viabus-architecture/blob/master/images/how_to_refact_project/add_100_area.webp)


## 抽象，顺应的是“开闭原则”

这是一帮对“抽象”无感的码农。

他们听到“抽象”，就像不爱锻炼的人听到父母、朋友劝其“健身”一样被动。

正如他们不理解健身的意义所在，他们也当抽象是“耳边风”。

“100 个地区”这种，天然的就是用工厂模式来抽象和定制，这原本是一目了然、毫无疑问的事。

重构后的代码，主页抬头特意标注了警告。

```
/
 * 友情提示：本类涂有防腐药品，切勿触碰，切勿触碰，切勿触碰！
 * <p>
 * 地区定制功能，包括特色的布局等，请继承于 AbstractDetailChildFragmentManager 单独编写！
 */

public class XXXDetailFragment extends BaseFragment implements IResponse {
    protected void initView() {
        initViewPagerManager();
        PagerAdapter pagerAdapter = new PagerAdapter();
        viewPagerFix.setOffscreenPageLimit(4);
        viewPagerFix.setAdapter(pagerAdapter);
        mFragmentBinding.tabLayout.setTabData(pagerAdapter.titles);
        mFragmentBinding.tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPagerFix.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        viewPagerFix.addOnPageChangeListener(new ViewPagerFix.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                KeyboardUtils.hideSoftInput(getActivity());
            }

            @Override
            public void onPageSelected(int position) {
                mFragmentBinding.tabLayout.setCurrentTab(position);
                mDetailChildFragmentManager.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * viewPager适配器
     */
    private class PagerAdapter extends FragmentPagerAdapter {

        String[] titles;

        PagerAdapter() {
            super(getChildFragmentManager());
            titles = mDetailChildFragmentManager.getTitles();
        }

        @Override
        public Fragment getItem(int position) {
            return mDetailChildFragmentManager.getItem(position);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Object object = super.instantiateItem(container, position);
            return mDetailChildFragmentManager.instantiateItem(container, position, object);
        }

        @Override
        public int getCount() {
            return mDetailChildFragmentManager.getCount();
        }
    }
}

```

## 代码是如何剪不断理还乱的？

听说过“代码耦合”和“解耦”的人很多，但真正理解这是怎么一回事的，**恐怕只有你 ~**

![](https://github.com/KunMinX/android-viabus-architecture/blob/master/images/how_to_refact_project/so_what.webp)


因为哪怕你不知，你也即将见证一位帅哥如何手把手带你解耦 ~

我们先来看下重构前的代码！

```
public interface XXXListNavigator {

    void updateRecyclerView();

    void showProgressDialog();

    void dismissProgressDialog();

    void updateListView();

    void updateLayerWrapperList(List<LayerWrapper> list);

    boolean isAnimationFinish();

    void resetCount();

}

public class XXXListViewModel extends BaseViewModel {
    public void multiAddOrRemove(ArrayList<String> bsms, boolean isAdd) {
        if (null != mNavigator) {
            mNavigator.showProgressDialog();
        }
        if (null == mMultiAddOrRemoveUseCase) {
            mMultiAddOrRemoveUseCase = new MultiAddOrRemoveUseCase();
        }
        mUseCaseHandler.execute(mMultiAddOrRemoveUseCase, new MultiAddOrRemoveUseCase.RequestValues(isAdd, bsms,
                        mLayerWrapperObservableField.get()),
                new UseCase.UseCaseCallback<MultiAddOrRemoveUseCase.ResponseValue>() {
                    @Override
                    public void onSuccess(MultiAddOrRemoveUseCase.ResponseValue response) {
                        ToastUtils.showShort(getApplicationContext(), "操作成功");
                        clearData();
                        loadData(true, true);
                        if (null != mNavigator) {
                            mNavigator.dismissProgressDialog();
                        }
                    }

                    @Override
                    public void onError() {
                        ToastUtils.showShort(getApplicationContext(), "操作失败");
                        if (null != mNavigator) {
                            mNavigator.dismissProgressDialog();
                        }
                    }
                });
    }
}

```

可以看到，UI 过度暴露了“处理 UI 逻辑所依赖的过程 API”，并在业务中直接干预了 UI 逻辑，这是典型的 MVP 写法，这造成了耦合。一旦 UI 的需求有变动，View 和 Presenter 的编写者都会受到牵连。

而且，职责过多造成了依赖过多，这个 Presenter 会因为过多的依赖，而越写越臃肿：受“破窗效应”的驱使，别的码农会因为此处已经有某个依赖，而不假思索的接着往下写。

## 到底怎样才算解耦

所谓解耦，是符合工程设计、符合设计模式原则的编码。

解耦的本质，我只说一遍：

**职责边界明确，职责边界明确，职责边界明确。**

![](https://github.com/KunMinX/android-viabus-architecture/blob/master/images/viabus_flow_flow_detail.png)


**符合单一职责原则：**
UI 的职责仅限于“展示”，也就是发送请求、处理 UI 逻辑。业务的职责仅限于“提供数据”，也就是接收请求、处理业务逻辑、响应结果数据。

**符合依赖倒置原则、最小知识原则：**
UI 不需要知道数据是经过怎样的周转得来的，它只需发送请求，并在拿到结果数据后，自己内部消化 UI 逻辑。业务只需处理数据并响应数据给 UI，它不需要知道 UI 会怎样使用数据，更无权干预。

综上，无论是 UI 还是业务，都不应过度暴露内部逻辑 API 而受控于人，**它们应只暴露请求 API，来响应外部的请求。过程逻辑应只在自己内部独立消化**。

```
public class XXXListBusinessProxy extends BaseBusiness<XXXBus> implements IXXXListFragmentRequest {

	@Override
    public void multiAddOrRemove(final XXXListDTO dto) {
        handleRequest((e) -> {
				...
                if (TextUtils.isEmpty(existBsms)) {
                    sendMessage(e, new Result(XXXDataResultCode.XXX_LIST_FRAGMENT_MULTI_ADD_OR_REMOVE, false));
                } else {
                    wyhcJgDBManager.insertAllTaskOfMine(existBsms, layersConfig);
                    sendMessage(e, new Result(XXXDataResultCode.XXX_LIST_FRAGMENT_MULTI_ADD_OR_REMOVE, true));
                }
                return null;
        });
    }

    @Override
    public void refreshPatternOfXXXList(final XXXListDTO dto) {
        handleRequest((e) -> {
				...
                count.setMyXXXCount(wyhcJgDBManager.getMyXXXPatternCount());
                return new Result(XXXDataResultCode.XXX_LIST_FRAGMENT_REFRESH_COUNT, count);
        });
    }

    @Override
    public void changeXXXPatternOfMine(final XXXListDTO dto) {
        handleRequest((e) -> {
                if (toMine) {
                    ...
                } else {
					...		
                    sendMessage(e, new Result(XXXDataResultCode.XXX_LIST_FRAGMENT_GET_ALL_PATTERN_OF_MINE, count));
                }
                return null;
        });
    }
}



public class XXXListFragment extends BaseFragment implements IResponse {

	XXXBus.XXX().queryList(mDto);
	
	XXXBus.XXX().multiAddOrRemove(mDto);
	
	XXXBus.XXX().queryPattern(mDto);

	...

	@Override
    public void onResult(Result testResult) {
        String code = (String) testResult.getResultCode();
        switch (code) {
            case XXXDataResultCode.XXX_LIST_FRAGMENT_REFRESH_LIST:
                updateRecyclerView((List<Wyhcrw>) testResult.getResultObject());
                if (isNeedUpdateCount()) {
                    ...
                } else {
                    finishLoading();
                }
                break;
            case XXXDataResultCode.XXX_LIST_FRAGMENT_MULTI_ADD_OR_REMOVE:
                if ((boolean) testResult.getResultObject()) {
                    loadData(true, true);
                } else {
                    ToastUtils.showShort(getContext(), "操作失败");
                }
                dismissProgressDialog();
                break;
			case XXXDataResultCode.XXX_LIST_FRAGMENT_REFRESH_PATTERN:
				...
				break;
			default:
        }
    }
}

```

## 解耦有什么好处？

解耦的好处，福特最有话语权。

100 多年前，福特发明了世界上第一条流水线，让工人职责边界明确，从而得以分工和专注各自领域。

原先装配一辆车需 700 小时，通过流水线分工后，平均一辆 12.5 小时，这使得生产效率提升了近 60 倍！

![](https://github.com/KunMinX/android-viabus-architecture/blob/master/images/how_to_refact_project/assemble_line.webp)


软件工程同理。

由于 UI 和业务职责边界明确，且相互通过接口通信，使得 UI 和业务的编写者能够**真正的分工**。

写 UI 的人，不会被业务的编写打断，他可以一气呵成的写自己的 UI。写业务的人，同样**不会被打断**，他可以专注于业务逻辑、数据结构和算法的优化。

写 UI 和写业务的人，都可以自己实现接口，去**独立的完成单元测试**，完全不必依赖和等候对方的实现。

最后，在职责边界明确的情况下，UI 就算写 100 个 UI 逻辑，那也是 UI，业务就算写 100 个业务，那也是业务，**纯种，所以不会杂乱**，何况我们还可以借助“接口隔离原则”继续往下分工！

...

## 总结

综上，本文介绍了两个重构思路：
**1.顺应开闭原则，对定制化功能进行抽象。**
**2.顺应单一职责、最小知识、依赖倒置原则，让职责边界明确，防止代码耦合。**

本次项目重构用到的，符合设计模式原则的 viabus 架构，已在 GitHub 开源。

如果你觉得本文对你有帮助的话，欢迎 Star & Fork。相信会有一天，你也可以，高效率的编写和重构代码！

![](https://github.com/KunMinX/android-viabus-architecture/blob/master/images/how_to_refact_project/good_luck.webp)


