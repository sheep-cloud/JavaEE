package cn.colg.bean;

import java.util.Date;

import cn.colg.core.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * - @mbg.generated
 *
 * @author colg
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class Ypxx extends BaseEntity {
    /** id */
    private String id;
    /** 药品流水号 */
    private String bm;
    /** 生产企业名称 */
    private String scqymc;
    /** 商品名 */
    private String spmc;
    /** 中标价 */
    private Float zbjg;
    /** 产品照片 */
    private String zpdz;
    /** 批准文号 */
    private String pzwh;
    /** 批准文号有效期 */
    private Date pzwhyxq;
    /** 是否进口药 */
    private String jky;
    /** 包装材质 */
    private String bzcz;
    /** 包装单位 */
    private String bzdw;
    /** 最新零售价 */
    private Float lsjg;
    /** 零售价出处 */
    private String lsjgcc;
    /** 质量层次 */
    private String zlcc;
    /** 质量层次说明 */
    private String zlccsm;
    /** 有无药品检验报告 */
    private String ypjybg;
    /** 药品检验报告编号 */
    private String ypjybgbm;
    /** 药品检验报告有效期 */
    private Date ypjybgyxq;
    /** 药品交易状态 */
    private String jyzt;
    /** 单位 */
    private String dw;
    /** 通用名 */
    private String mc;
    /** 剂型 */
    private String jx;
    /** 规格 */
    private String gg;
    /** 转换系数 */
    private String zhxs;
    /** 通用名拼音 */
    private String pinyin;
    /** 药品类别 */
    private String lb;
    /** 产品说明 */
    private String cpsm;
    /** 价格 */
    private String price;

    /// ----------------------------------------------------------------------------------------------------

}