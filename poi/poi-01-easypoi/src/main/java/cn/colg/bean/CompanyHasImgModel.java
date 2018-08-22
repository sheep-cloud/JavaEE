package cn.colg.bean;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.colg.core.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 公司 - 导出（图片）
 *
 * @author colg
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class CompanyHasImgModel extends BaseEntity {

    /** 公司id */
    private String companyId;

    /** 公司名称 */
    @Excel(name = "公司名称")
    private String companyName;

    /** 公司logo */
    @Excel(name = "公司LOGO", type = 2, width = 64, height = 64, imageType = 1)
    private String companyLogo;

    /** 公司地址 */
    @Excel(name = "公司地址", width = 20)
    private String companyAddress;

}
