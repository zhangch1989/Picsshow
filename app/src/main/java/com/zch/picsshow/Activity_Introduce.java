package com.zch.picsshow;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.zch.picsshow.Activity_Project;
import com.zch.picsshow.Activity_Studio;
import com.zch.picsshow.R;
import com.zch.picsshow.base.BaseActivity;

/**
 * Created by Zch on 2020/4/25 11:30.
 * 集团简介
 */
public class Activity_Introduce extends BaseActivity {
    private final static String TAG = "Activity_Introduce";
    private Context context;

    private ImageButton ibtn_back;
    private Button btn_project, btn_studio, btn_first;
    private TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8, tv9, tv10, tv11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduce);
        context = this;
        initView();
        initEvent();
    }

    private void initView(){
        ibtn_back = (ImageButton) findViewById(R.id.btn_back);
        btn_project = (Button) findViewById(R.id.btn_project);
        btn_studio = (Button) findViewById(R.id.btn_studio);
        btn_first = (Button) findViewById(R.id.btn_first);

        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
        tv4 = (TextView) findViewById(R.id.tv4);
        tv5 = (TextView) findViewById(R.id.tv5);
        tv6 = (TextView) findViewById(R.id.tv6);
        tv7 = (TextView) findViewById(R.id.tv7);
        tv8 = (TextView) findViewById(R.id.tv8);
        tv9 = (TextView) findViewById(R.id.tv9);
        tv10 = (TextView) findViewById(R.id.tv10);
        tv11 = (TextView) findViewById(R.id.tv11);
        initData();
    }

    private void initData(){
        tv1.setVisibility(View.GONE);
        tv1.setText("一、市环卫集团基本情况");
        tv2.setText("\t\t\t\t2002年12月市政府出资成立重庆市环卫集团，注册资金10亿元，授权市环卫集团负责固废垃圾处理与收运等环卫基础设施建设和运营管理工作。目前市环卫集团现有职工3000余人。集团领导班子成员8人，集团本部下设党群及人力资源部（办公室）、投资发展部等8个部门，拥有市固废处理公司、市固废运输公司、水域公司等20家子公司。主营业务：生活垃圾填埋、餐厨垃圾、建筑垃圾、一般工业废弃物等各类固废垃圾收运处理、水域清漂、主城区道路桥梁隧道护板护栏机械化清洗保洁等，形成了涵盖生活垃圾、餐厨垃圾、建筑垃圾等较为完整的固废垃圾处置产业链，产业门类齐全、业务收入稳定，业务范围遍及全市23个区（县）。2019年市环卫集团收运处理生活垃圾、餐厨垃圾、果蔬垃圾、一般工业废弃物等各类固废垃圾约1.8万吨/日。市环卫集团是确保垃圾日产日清、保障城市正常运行的重要力量。");
        tv3.setText("\t\t\t\t（一）全国率先构建覆盖城乡的餐厨垃圾收运处理系统。为清除餐厨垃圾带来的食品安全隐患，2009年市环卫集团全国率先开展了餐厨垃圾无害化处理和资源化利用工作，建成投用黑石子餐厨垃圾处理厂，全国率先采用世界领先的“高温厌氧、热电联产”工艺技术处理餐厨垃圾，利用餐厨垃圾生产生物柴油、CNG和电能等。黑石子餐厨厂已累计处理餐厨垃圾约300万吨，日均1800吨。同时，市环卫集团积极参与区县餐厨垃圾处理，建设黔江、合川、涪陵、永川、綦江、忠县片区餐厨垃圾处理中心，处理周边23个区（县）收运的城镇、农村餐厨垃圾。目前，黔江、合川、涪陵片区餐厨垃圾处理中心已建成投用，永川、綦江片区餐厨垃圾处理中心已开工建设。市环卫集团餐厨垃圾处理规模和处理技术全国领先。");
        tv4.setText("\t\t\t\t（二）全国率先构建城市生活垃圾智慧化清洁运输网络物流系统。集团全国率先实施主城区生活垃圾清洁运输工程，升级改造主城区生活垃圾前端收集和一次中转系统，彻底解决垃圾运输污染环境问题。全国率先建成首座多功能生态垃圾转运站西永站，实现生活垃圾、餐厨垃圾零污染一体化转运。全国率先构建跨区域大型生活垃圾二次转运系统，建成界石、走马和夏家坝3座大型垃圾分类物流基地，转运规模达11000吨/日，转运规模全国第一。目前市环卫集团日均转运垃圾约14000吨。");
        tv5.setText("\t\t\t\t（三）全国率先实施建筑垃圾资源化利用工程。集团投资2.7亿元建成黑石子和南岸广阳两座建筑弃料资源化利用厂，利用建筑弃料生产骨料、标准砖等新型环保再生建材，年处理规模达140万吨，实现建筑垃圾无害化处理和资源化利用。同时，为彻底解决黑石子片区环境问题，根据市政府决定，黑石子建筑弃料资源化利用厂现已停用，土地正待移交江北区政府统一开发。");
        tv6.setText("\t\t\t\t（四）担负主城区生活垃圾“兜底”处理重任，避免发生垃圾围城。根据市政府要求，2015年底黑石子垃圾处理场开场投用，“兜底”处理主城区生活垃圾。黑石子场累计处理生活垃圾400多万吨，数十次在垃圾焚烧厂停机检修时应急处理调运的全部生活垃圾，为避免主城区发生垃圾围城发挥了巨大作用。2019年6月底黑石子场停用，渝北洛碛生活垃圾应急填埋场（一期）建成投用，继续担负主城区生活垃圾“兜底”处理重任。目前，洛碛场运行平稳。");
        tv7.setText("\t\t\t\t（五）抓紧建设洛碛垃圾分类处理园等“十三五”重大环卫基础设施项目。为贯彻落实中央、市委市政府垃圾分类精神，根据市政府要求，市环卫集团全国率先建设规模最大、种类最全的洛碛垃圾分类处理园，2018年被国家发改委和住建部确定为国家资源循环利用基地。洛碛垃圾分类处理园总投资约50亿元，占地5000亩，生活垃圾焚烧填埋、餐厨垃圾厨余垃圾厌氧消化、生物柴油，废旧塑料生产轻柴油，废旧塑料瓶生产化工原料，建筑垃圾生产再生建材，年处理各类固废垃圾381万吨，年生产生物柴油5万吨、发电5.5亿度、轻柴油8.1万吨、肥料10万吨、化工原料1800吨、透水砖等环保建材50万立方米，实现垃圾分类处理。目前，园区内生活垃圾应急填埋场（一期）已建成投用，易腐垃圾处理厂今年底建成投用。其余设施正陆续开工建设。");
        tv8.setText("\t\t\t\t（六）积极拓展垃圾分类市场。市环卫集团积极延伸垃圾分类产业链，积极拓展垃圾分类市场，与九龙坡、綦江、永川、武隆等12个区县签订生活垃圾收运和餐厨垃圾分类收运处理的特许经营协议，协助各区县政府建设垃圾分类示范小区、分类收集、分类运输垃圾。研发大数据系统实现垃圾收运处理全生命周期智慧管理，像管理快件一样实现垃圾全过程动态管理。全国率先党员引领科技创新推动垃圾分类工作，垃圾分类成本低、效果好、可持续、可推广，仅10元/户•月。");
        tv9.setText("\t\t\t\t（七）创新发展，高新技术创新成果丰硕。市环卫集团建成国家高新技术产业、全国唯一环卫领域院士工作站、重庆市工程技术研究中心、博士后科研工作站，多个研发项目被列入国家和省部级研发课题，技术创新成果获得市科技进步一、二、三等奖。一是全国率先掌握餐厨垃圾处理核心技术。开发出适应我国餐厨垃圾特点的国产化成套设备，形成独有的知识产权，打破国外技术垄断。二是全国率先掌握有机垃圾厌氧消化核心技术。全国率先实现果蔬垃圾、污泥和餐厨垃圾联合厌氧消化生产新能源，开辟了果蔬垃圾、污泥资源化利用新途径，实现环卫领域中国创造，取得100余项环卫领域国家新型技术专利。");
        tv10.setText("\t\t\t\t同时，市环卫集团还承担主城区长江、嘉陵江126公里水域、铜梁涪江、琼江、巴川河、淮远河200公里水域清漂保洁、主城区道路桥梁隧道护板护栏机械化清扫保洁、12个区县垃圾收运等工作。");
        tv11.setText("\t\t\t\t在市财政局、市城市管理局等市级部门大力支持下，市环卫集团发展成为全国同类型企业中技术水平最先进、业务领域最齐全的龙头企业。北京、上海、广州等全国几乎所有大城市均来考察学习。中央电视台等媒体多次正面报道市环卫集团。市环卫集团荣获全国文明单位、全国工人先锋号、全国模范职工之家、国家“高新技术企业”称号等众多荣誉，涌现出全国劳动模范、富民兴渝贡献奖获得者、全国先进环卫工作者等一大批先进模范典型。敏尔书记、良智市长等多位市领导对市环卫集团工作给与高度评价。");

        setTextViewColor(tv3);
        setTextViewColor(tv4);
        setTextViewColor(tv5);
        setTextViewColor(tv6);
        setTextViewColor(tv7);
        setTextViewColor(tv8);
        setTextViewColor(tv9);
        tv10.setLineSpacing(1, 1.2f);
        tv11.setLineSpacing(1, 1.2f);
    }

    private void initEvent(){
        ibtn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
        btn_project.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, Activity_Project.class));
            }
        });

        btn_studio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, Activity_Studio.class));
            }
        });
    }

    private void setTextViewColor(TextView tv){
        String str = tv.getText().toString();
        SpannableStringBuilder builder = new SpannableStringBuilder(str);
        //ForegroundColorSpan 为文字前景色，BackgroundColorSpan为文字背景色
        ForegroundColorSpan redSpan = new ForegroundColorSpan(getResources().getColor(R.color.top_color));
        builder.setSpan(redSpan, 0, str.split("。")[0].length() +1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setText(builder);
        tv.setLineSpacing(1, 1.2f);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
