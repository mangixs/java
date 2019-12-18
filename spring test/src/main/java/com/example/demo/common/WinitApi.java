package com.example.demo.common;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.WintiRequestMsg;
import com.example.demo.utils.SignUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class WinitApi {
    private static final Logger logger = LoggerFactory.getLogger(WinitApi.class);
    private String token;
    private String appKey;
    private String url;
    private String trackUrl;
    private String clientId;
    private String clientSecret;
    private String platforms;
    private String activeUrl;
    private String format = "json";
    private String version = "1.0";
    private String signMethod = "md5";
    private String language = "zh_CN";
    private Map<String, Object> configList;

    public void setConfig(String account){
        Map<String, String> config = new HashMap();
        config = (Map<String, String>) configList.get(account);
        if (config.isEmpty()){
            config = (Map<String, String>) configList.get("default");
        }
        this.token = config.get("token");
        this.appKey = config.get("appKey");
        this.clientId = config.get("clientId");
        this.url = config.get("url");
        this.trackUrl = config.get("trackUrl");
        this.clientSecret = config.get("clientSecret");
        this.platforms = config.get("platforms");
    }

    public void setConfigList(Map<String, Object> configList){
        this.configList = configList;
    }

    private void setBaseRequestInfo(WintiRequestMsg requestMsg){
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timeStamp = df.format(LocalDateTime.now());
        requestMsg.setApp_key(this.appKey);
        requestMsg.setFormat(this.format);
        requestMsg.setVersion(this.version);
        requestMsg.setSign_method(this.signMethod);
        requestMsg.setTimestamp(timeStamp);
        requestMsg.setLanguage(this.language);
        requestMsg.setPlatform(this.platforms);
        requestMsg.setClient_id(this.clientId);
    }

    private void setBaseRequestAction(WintiRequestMsg requestMsg, String action){
        requestMsg.setAction(action);
    }

    private void setRequestUserSign(WintiRequestMsg requestMsg, String token){
        String sign = SignUtil.getSign(requestMsg, token);
        requestMsg.setSign(sign);
    }

    private void setRequestClientSign(WintiRequestMsg requestMsg, String clientSecret){
        String clientSign = SignUtil.getSign(requestMsg, clientSecret);
        requestMsg.setClient_sign(clientSign);
    }

    private JSONObject sendRequest(Map<String, Object> data, String action) {
        WintiRequestMsg requestMsg = new WintiRequestMsg();
        requestMsg.setData(data);
        setBaseRequestInfo(requestMsg);
        setBaseRequestAction(requestMsg, action);
        setRequestUserSign(requestMsg, this.token);
        setRequestClientSign(requestMsg, this.clientSecret);
        String postData = JSONObject.toJSONString(requestMsg);
        logger.info("postWinitData: {}", postData);
        JSONObject res = null;
        try {
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost httpPost = new HttpPost(this.activeUrl);
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(300000).setConnectTimeout(300000).build();
            httpPost.setConfig(requestConfig);
            StringEntity postEntity = new StringEntity(postData, "UTF-8");
            httpPost.addHeader("Content-Type", "application/json;");
            httpPost.setEntity(postEntity);
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            String ret =  EntityUtils.toString(httpEntity, "UTF-8");
            logger.info("winit-return-data: {}", ret);
            res = JSONObject.parseObject(ret);
        } catch (Exception e){
            logger.info("postWintiErrorInfo: {}", e);
        }
        return res;
    }
    private void setNormalUrl(){
        this.activeUrl = this.url;
    }
    private void setTrackUrl(){
        this.activeUrl = this.trackUrl;
    }
    /**
    * @Description: 查询所有仓库
    * @Param: []
    * @return: com.alibaba.fastjson.JSONObject
    * @Author: helios
    * @Date: 2019/12/10 0010
    */
    public JSONObject queryAllWarehouse() {
        String action = "queryWarehouse";
        this.setNormalUrl();
        Map<String, Object> data = new HashMap();
        return sendRequest(data, action);
    }

    /**
    * @Description: 查询指定仓库的商品DOI数据
    * @params pageNum 页码 request
    * @params pageSize 每页显示数量 request
    * @params warehouseId 仓库ID，多个仓库之间用逗号隔开 option
    * @params productCode sku编码 可以为空 option
    * @params inventoryType 库存类型：Country：国家，Warehouse：仓库 request
    * @params inReturnInventory N 不查询退货库存 Y 查询退货库存 option
    * @return: com.alibaba.fastjson.JSONObject
    * @Author: helios
    * @Date: 2019/12/10 0010
    */
    public JSONObject queryProductInventoryList4Page(Map<String, Object> data) {
        String action = "queryProductInventoryList4Page";
        this.setNormalUrl();
        return sendRequest(data, action);
    }
    /**
     * 查询总库存-1 通过本接口用户可以查询指定海外仓的所有商品库存数量。
     *
     * @params [type] $warehouseId
     * @params string $isActive
     * @params string $inReturnInventory
     * @params string $pageSize
     * @params string $pageNum
     * @return: com.alibaba.fastjson.JSONObject
     * @Author: helios
     * @Date: 2019/12/10 0010
     */
    public JSONObject queryWarehouseStorage(Map<String, Object> data) {
        String action = "queryWarehouseStorage";
        this.setNormalUrl();
        return sendRequest(data, action);
    }
    /**
     * 卖家或第三方通过该接口可查询出库单。
     *
     * @params [type] $dateOrderedStartDate  yyyy-MM-dd 出库单时间
     * @params [type] $dateOrderedEndDate yyyy-MM-dd 出库单时间
     * @params [type] $pageSize 每页显示数量
     * @params [type] $pageNum 页码
     * @params string $status 组合状态： valid :[有效单]  outbound :[已出库] stockup :[海外仓备货] exception :[异常]
     * @return: com.alibaba.fastjson.JSONObject
     * @Author: helios
     * @Date: 2019/12/10 0010
     */
    public JSONObject queryOutboundOrderList(Map<String, Object> data){
        String action = "queryOutboundOrderList";
        this.setNormalUrl();
        return sendRequest(data, action);
    }
    /**
     * 创建出库单并提交至“确认中”状态。当出库单状态在“确认中”后不能再更改状态。
     *
     * @params array $data 参数见 http://developer.winit.com.cn/document/detail/id/49.html
     * @params bool $returnJson 是否只返回json数据
     * @return: com.alibaba.fastjson.JSONObject
     * @Author: helios
     * @Date: 2019/12/10 0010
     */
    public JSONObject createOutboundOrder(Map<String, Object> data) {
        String action = "createOutboundOrder";
        this.setNormalUrl();
        return sendRequest(data, action);
    }

    /**
     * 单次或批量查询已提交的海外仓出库单详细信息、状态。
     *
     * @params [type] $outboundOrderNum 海外仓出库单号在用户创建海外仓出库单后获得，唯一
     * @return: com.alibaba.fastjson.JSONObject
     * @Author: helios
     * @Date: 2019/12/10 0010
     */
    public JSONObject queryOutboundOrder(String outboundOrderNum) {
        String action = "queryOutboundOrder";
        this.setNormalUrl();
        Map<String, Object> data = new HashMap();
        data.put("outboundOrderNum", outboundOrderNum);
        return sendRequest(data, action);
    }

    /**
     * 出库订单库内和尾程派送轨迹。
     *
     * @params [type] $trackingnos 出库订单号或快递单号，批量查询时使用逗号(,)或空格分隔
     * @return: com.alibaba.fastjson.JSONObject
     * @Author: helios
     * @Date: 2019/12/10 0010
     */
    public JSONObject getOrderVerdorTracking(String trackingnos) {
        String action = "tracking.getOrderVerdorTracking";
        this.setTrackUrl();
        Map<String, Object> data = new HashMap();
        data.put("trackingnos", trackingnos);
        return sendRequest(data, action);
    }

    /**
     * 查询商品信息功能。
     *
     * @params [type] $pageNo 页码
     * @params [type] $pageSize 每页数量
     * @return: com.alibaba.fastjson.JSONObject
     * @Author: helios
     * @Date: 2019/12/10 0010
     */
    public JSONObject getAllProduct(Map<String, Object> data) {
        String action = "winit.mms.item.list";
        this.setTrackUrl();
        return sendRequest(data, action);
    }

    /**
     * 查询订单（详情）
     *
     * @params [type] $orderNo WINIT订单号
     * @params string $isIncludePackage 返回结果是否包含包裹信息。Y-是,N-否,默认N。
     * @return: com.alibaba.fastjson.JSONObject
     * @Author: helios
     * @Date: 2019/12/10 0010
     */
    public JSONObject getOrderDetail(String orderNo, String isIncludePackage) {
        String action = "winit.wh.inbound.getOrderDetail";
        this.setTrackUrl();
        Map<String, Object> data = new HashMap();
        data.put("orderNo", orderNo);
        data.put("isIncludePackage", isIncludePackage);
        return sendRequest(data, action);
    }

    /**
     * 查询订单（列表）
     *
     *  $data数组包括
     *
     * @params integer $pageNo 第几页 require
     * @params integer $pageSize 一页多少数据 require
     * @params string $winitProductCode WINIT产品编码 require     *
     * @params [type] $orderType 订单类型,入库方式： SD-标准海外仓入库 DW-直发海外验入库 DI-直发国内验入库 SE-标准存储仓入库 SI-直发国内验存储仓入库 option
     * @params [type] $destnationWareHouseCode 目的仓编码 option
     * @params [type] $inspectionWareHouseCode 验货仓编码 option
     * @params [type] $orderCreateDateEnd 结束下单时间 option
     * @params [type] $orderCreateDateStart 起始下单时间 option
     * @return: com.alibaba.fastjson.JSONObject
     * @Author: helios
     * @Date: 2019/12/10 0010
     */
    public JSONObject getOrderList(Map<String, Object> data) {
        String action = "winit.wh.inbound.getOrderList";
        this.setTrackUrl();
        return sendRequest(data, action);
    }

    /**
     * 注册商品
     *
     * @params [type] $data https://developer.winit.com.cn/document/detail/id/15.html
     * @return: com.alibaba.fastjson.JSONObject
     * @Author: helios
     * @Date: 2019/12/10 0010
     */
    public JSONObject registerProduct(Map<String, Object> data) {
        String action = "registerProduct";
        this.setNormalUrl();
        return sendRequest(data, action);
    }

    /**
     * 查询退货订单列表
     * status 草稿-PD 已下单-OD 已收件-RE 待处理-WP 已完成-OC 已销毁-DT 已取消-CD
     *
     * @params [type] $pageNo 页码
     * @params [type] $pageSize 每页大小
     * @params [type] $startTime 开始时间
     * @params date $OderEndDatetime 结束时间
     * @return: com.alibaba.fastjson.JSONObject
     * @Author: helios
     * @Date: 2019/12/10 0010
     */
    public JSONObject queryReturnOderList(Map<String, Object> data) {
        String action = "rma.returnGoodsOrder.queryReturnOderList";
        this.setTrackUrl();
        return sendRequest(data, action);
    }

    /**
     * 查询单个退货单
     *
     * @params [type] $returnGoodsOrderNo  WINIT订单号-退货
     * @return: com.alibaba.fastjson.JSONObject
     * @Author: helios
     * @Date: 2019/12/10 0010
     */
    public JSONObject singleQueryReturnOrderList(String returnGoodsOrderNo) {
        String action = "rma.returnGoodsOrder.queryReturnOderList";
        this.setTrackUrl();
        Map<String, Object> data = new HashMap();
        data.put("returnGoodsOrderNo", returnGoodsOrderNo);
        return sendRequest(data, action);
    }

    /**
     * 取消订单
     *
     * @params [type] $outboundOrderNum 订单号
     * @return: com.alibaba.fastjson.JSONObject
     * @Author: helios
     * @Date: 2019/12/10 0010
     */
    public JSONObject voidOutboundOrder(String outboundOrderNum) {
        String action = "voidOutboundOrder";
        this.setNormalUrl();
        Map<String, Object> data = new HashMap();
        data.put("outboundOrderNum", outboundOrderNum);
        return sendRequest(data, action);
    }

    /**
     * 验证德国地址是否正确
     *
     * @params [type] $data
     * @return: com.alibaba.fastjson.JSONObject
     * @Author: helios
     * @Date: 2019/12/10 0010
     */
    public JSONObject addressIsValid(Map<String, Object> data) {
        String action = "winit.tools.address.isValid";
        this.setTrackUrl();
        return sendRequest(data, action);
    }

    /**
     * 查询入库订单费用
     * $data = [
     *         'businessDocumentNo' => $inboundOrder, 海外仓入库单号,在用户创建海外仓t入库单后获得，唯一
     *          'pageParams' => [
     *               'pageNo' => '1',
     *               'pageSize' => 50,
     *             ],
     *       ];
     * @params [type] $data
     * @return: com.alibaba.fastjson.JSONObject
     * @Author: helios
     * @Date: 2019/12/10 00
     */
    public JSONObject pageInboundLine(Map<String, Object> data) {
        String action = "sms.incomeSettlement.pageInboundLine";
        this.setTrackUrl();
        return sendRequest(data, action);
    }

    /**
     * 查询入库单轨迹
     *
     * @params string $orderNo 入库单编码
     * @return: com.alibaba.fastjson.JSONObject
     * @Author: helios
     * @Date: 2019/12/10 00
     */
    public JSONObject queryOrderTracking(String orderNo) {
        String action = "wh.tracking.queryOrderTracking";
        this.setTrackUrl();
        Map<String, Object> data = new HashMap();
        data.put("orderNo", orderNo);
        return sendRequest(data, action);
    }

    /**
     * 查询进口报关规则
     *
     * @params string $endWarehouseCode  目的仓库编码
     * @params string $productCode 产品编码
     * @return: com.alibaba.fastjson.JSONObject
     * @Author: helios
     * @Date: 2019/12/10 00
     */
    public JSONObject importDeclarationRule(String endWarehouseCode, String productCode) {
        String action = "winit.ups.importDeclarationRule.queryList";
        this.setTrackUrl();
        Map<String, Object> data = new HashMap();
        data.put("endWarehouseCode", endWarehouseCode);
        data.put("productCode", productCode);
        return sendRequest(data, action);
    }

    /**
     * 查询EOR类型
     * EOR类型查询,仅支持入库单，依示例代码查询。如果选择的EOR类型是CEOR（客户自有的），则才需要选择相应的出口商数据；否则，无需选择。
     * @return: com.alibaba.fastjson.JSONObject
     * @Author: helios
     * @Date: 2019/12/10 00
     */
    public JSONObject queryEorList() {
        String action = "winit.ups.eorType.queryEorList";
        this.setTrackUrl();
        Map<String, Object> data = new HashMap();
        return sendRequest(data, action);
    }

    /**
     * 上传入库订单的报关资料。
     *
     * @params string $orderNo 入库单号
     * @params string $docType 上传资料类型：EXPORT-出口；IMPORT-进口
     * @params array $customsDeclarationDocs 文件名,一个订单不能超过10个文件 文件类型,支持jpg, gif, png, jpeg, pdf, doc, docx, xls, csv, xlsx， 不超过5M。文件数据,注意：文件转成byte数组
     *              $customsDeclarationDocs = [['fileName' => 'test', 'fileType' => 'jpg', 'filePath' => './test.jpg']];
     * @return: com.alibaba.fastjson.JSONObject
     * @Author: helios
     * @Date: 2019/12/10 00
     */
    public JSONObject uploadCustomsDeclareDocs(Map<String, Object> data) {
        String action = "winit.wh.inbound.order.uploadCustomsDeclareDocs";
        this.setTrackUrl();
        return sendRequest(data, action);
    }

    /**
     * 费用预估
     *
     * @params string $orderNo 订单编码
     * @return: com.alibaba.fastjson.JSONObject
     * @Author: helios
     * @Date: 2019/12/10 00
     */

    public JSONObject estimateOrderFee(String orderNo) {
        String action = "winit.wh.inbound.estimateOrderFee";
        this.setTrackUrl();
        Map<String, Object> data = new HashMap();
        data.put("orderNo", orderNo);
        return sendRequest(data, action);
    }

    /**
     * 获取提货地址
     *
     * @params [type] $pageNo
     * @params [type] $pageSize
     * @return: com.alibaba.fastjson.JSONObject
     * @Author: helios
     * @Date: 2019/12/10 00
     */
    public JSONObject getPickupAddress(int pageNo, int pageSize) {
        String action = "winit.ums.getPickupAddress";
        this.setTrackUrl();
        Map<String, Object> data = new HashMap();
        Map<String, Object> page = new HashMap();
        page.put("pageNo", pageNo);
        page.put("pageSize", pageSize);
        data.put("pageParams", page);
        return sendRequest(data, action);
    }

    /**
     * 获取发货供应商
     *
     * @return: com.alibaba.fastjson.JSONObject
     * @Author: helios
     * @Date: 2019/12/10 00
     */
    public JSONObject getCarriers() {
        String action = "winit.ups.carrier.getCarriers";
        this.setTrackUrl();
        Map<String, Object> data = new HashMap();
        data.put("shipperType", "CUSTOMER");
        return sendRequest(data, action);
    }

    /**
     * 获取进出口供应商
     *
     * @params string $countryCode 国家编码,通用国家二字编码
     * @params string $vendorType  类型：IOR-进口商,EOR-出口商
     * @return: com.alibaba.fastjson.JSONObject
     * @Author: helios
     * @Date: 2019/12/10 00
     */
    public JSONObject getVendorInfo(String countryCode, String vendorType) {
        String action = "winit.ums.getVendorInfo";
        this.setTrackUrl();
        Map<String, Object> data = new HashMap();
        data.put("countryCode", countryCode);
        data.put("vendorType", vendorType);
        return sendRequest(data, action);
    }

    /**
     * 获取物流计划
     *
     * @params string $winitProductCode WINIT产品编码
     * @params string $inspectionWarehouseCode 验货仓编码
     * @params string $destinationWarehouseCode 目的仓编码
     * @return: com.alibaba.fastjson.JSONObject
     * @Author: helios
     * @Date: 2019/12/10 00
     */
    public JSONObject getLogisticsPlan(String winitProductCode, String inspectionWarehouseCode, String destinationWarehouseCode) {
        String action = "winit.tms.getLogisticsPlan";
        this.setTrackUrl();
        Map<String, Object> data = new HashMap();
        data.put("winitProductCode", winitProductCode);
        data.put("inspectionWarehouseCode", inspectionWarehouseCode);
        data.put("destinationWarehouseCode", destinationWarehouseCode);
        return sendRequest(data, action);
    }

    /**
     * 可选商品查询,根据WINIT产品、头程类型及目的仓筛选允许下单的商品列表。
     *
     * @params string $customerCode 客户编码
     * @params string $firstLegType 头程类型：WC-Winit承运,NS-直发,说明：标准海外仓入库的头程类型为WC,国内直发验入库与海外直发验入库的头程类型为NS
     * @params string $winitProductCode WINIT产品编码
     * @params string $destinationWarehouseCode 目的仓编码
     * @return: com.alibaba.fastjson.JSONObject
     * @Author: helios
     * @Date: 2019/12/10 00
     */
    public JSONObject getAvailableMerchandise(String customerCode, String firstLegType, String winitProductCode, String destinationWarehouseCode) {
        String action = "winit.wh.mms.getAvailableMerchandise";
        this.setTrackUrl();
        Map<String, Object> data = new HashMap();
        data.put("customerCode", customerCode);
        data.put("firstLegType", firstLegType);
        data.put("winitProductCode", winitProductCode);
        data.put("destinationWarehouseCode", destinationWarehouseCode);
        return sendRequest(data, action);
    }

    /**
     * 获取验货仓或目的仓
     *
     * @params string $winitProductCode WINIT产品编码
     * @params string $warehouseType  验货仓： INSJ  目的仓： DEST
     * @params string $orderType 订单类型 1.SD-标准海外仓入库
     * @params string $inspectionWarehouseCode 验货仓编码,当warehouseType=DEST时，必填
     * @return: com.alibaba.fastjson.JSONObject
     * @Author: helios
     * @Date: 2019/12/10 00
     */
    public JSONObject getWarehouseList(Map<String, Object> data) {
        String action = "winit.pms.getWarehouseList";
        this.setTrackUrl();
        return sendRequest(data, action);
    }

    /**
     * 卖家或第三方通过该接口查询Winit服务产品。
     *
     * @params string $productType产品类型：
     *       OW0101-标准海外仓入库
     *       OW0102-直发国内验入库
     *       OW0103-直发海外验入库
     *       SSW11-标准存储仓入库
     *       SSW12-直发存储仓
     * @return: com.alibaba.fastjson.JSONObject
     * @Author: helios
     * @Date: 2019/12/10 00
     */
    public JSONObject getWinitProducts(String productType) {
        String action = "winit.wh.pms.getWinitProducts";
        this.setTrackUrl();
        Map<String, Object> data = new HashMap();
        data.put("productType", productType);
        return sendRequest(data, action);
    }

    /**
     * 创建入库单
     *
     * @params [type] $data https://developer.winit.com.cn/document/detail/id/93.html
     * @return: com.alibaba.fastjson.JSONObject
     * @Author: helios
     * @Date: 2019/12/10 00
     */
    public JSONObject createInboundOrder(Map<String, Object> data) {
        String action = "winit.wh.inbound.order.create";
        this.setTrackUrl();
        return sendRequest(data, action);
    }

    /**
     * 打印单品条码标签
     *
     * @params [type] $singleItems  require
     *  $singleItems = [
     *   [
     *       'productCode'=>'' 客户注册产品时命名的产品编码，不可修改 require
     *       'specification' => '' 产品规格 option
     *        'printQty'=>'' 打印数量，<1000 require
     *   ]
     * ]
     * @params [type] $labelType 条码尺寸类型 LZ10060：100x60mm仅有单品条码（默认）； LZ7050：70x50mm，仅有单品条码； LZ6040:60x40mm，仅有单品条码 require
     * @params string $madeIn None:不显示信息在条码 China: 产地显示中国 require
     * @return: com.alibaba.fastjson.JSONObject
     * @Author: helios
     * @Date: 2019/12/10 00
     */
    public JSONObject printSkuLabel(String singleItems, String labelType, String madeIn) {
        String action = "winit.singleitem.label.print.v2";
        this.setTrackUrl();
        Map<String, Object> data = new HashMap();
        data.put("singleItems", singleItems);
        data.put("labelType", labelType);
        data.put("madeIn", madeIn);
        return sendRequest(data, action);
    }

    /**
     * 包裹标签批量打印，仅支持入库单，每批次最多打印50个包裹标签。
     *
     * @params [type] $orderNo Winit订单号
     * @params [type] $labelType  打印尺寸： 10x10,10x6,15x10
     * @params [type] $pageNoList 包裹列表，如下为包裹子节点。说明：每次打印包裹数量限制为50个，若超过请分批调用。 包裹条码 卖家箱单号
     * @return: com.alibaba.fastjson.JSONObject
     * @Author: helios
     * @Date: 2019/12/10 00
     */
    public JSONObject printPackageLabel(String orderNo, String packageNoList, String labelSize) {
        String action = "winit.wh.inbound.printPackageLabel";
        this.setTrackUrl();
        Map<String, Object> data = new HashMap();
        data.put("orderNo", orderNo);
        data.put("packageNoList", packageNoList);
        data.put("labelSize", labelSize);
        return sendRequest(data, action);
    }

    /**
     * 查询商品提交注册后的审核状态
     * status 商品审核状态 DR 待维护 PD 已发布 CO 维护完成 BK 退回 WSA 验证中
     * @params [type] $data ['skuCode' => '','importCountryCode' => 'US', 'pageVo' => ['pageNo' => 1 ,'pageSize' => 10]]; 后面三个 require
     * @return: com.alibaba.fastjson.JSONObject
     * @Author: helios
     * @Date: 2019/12/10 00
     */
    public JSONObject queryItemMtEntitys(Map<String, Object> data) {
        String action = "mms.itemmttask.queryItemMtEntitys";
        this.setTrackUrl();
        return sendRequest(data, action);
    }

    /**
     * 创建退货订单
     *
     * @params [type] $data
     * @return: com.alibaba.fastjson.JSONObject
     * @Author: helios
     * @Date: 2019/12/10 00
     */
    public JSONObject createReturnGoodsOrder(Map<String, Object> data) {
        String action = "rma.returnGoodsOrder.createReturnGoodsOrder";
        this.setTrackUrl();
        return sendRequest(data, action);
    }

    /**
     * 查询派送费用
     * 卖家或第三方通过该接口：使用出库订单号、跟踪号、卖家订单号、出库仓和下单日期查询出库订单的订单费用信息
     *
     * @return: com.alibaba.fastjson.JSONObject
     * @Author: helios
     * @Date: 2019/12/10 00
     */
    public JSONObject queryOutboundFee(Map<String, Object> data) {
        String action = "sms.incomeSettlement.queryOutboundOrderFee";
        this.setTrackUrl();
        return sendRequest(data, action);
    }

    /**
     * 查询sku库龄
     *
     * $params = [
     *       'merchandiseCode' => 'sku1', 选填
     *       'warehouseCode' => 'DE0001', 选填
     *      'startTime' => '2019-09-10', 开始时间
     *      'endTime' => '2019-09-10', 结束时间
     *      'pageNo' => 1, 页码
     *      'pageSize' => 100 每页长度
     *   ];
     * 如果输入商品编码或商品条码，开始时间和结束时间跨度不能超过30天
     * 如果未输入商品编码或商品条码，开始时间和结束时间跨度不能超过1天
     * @params [type] $data
     * @return: com.alibaba.fastjson.JSONObject
     * @Author: helios
     * @Date: 2019/12/10 00
     */
    public JSONObject querySkuAge(Map<String, Object> data) {
        String action = "winit.ims.queryWarehouseReceiptDetails";
        this.setTrackUrl();
        return sendRequest(data, action);
    }

}
