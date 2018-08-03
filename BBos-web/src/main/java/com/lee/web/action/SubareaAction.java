package com.lee.web.action;

import com.lee.domain.Region;
import com.lee.domain.Subarea;
import com.lee.service.ISubareaService;
import com.lee.utils.FileUtils;
import com.lee.web.action.base.BaseAction;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletOutputStream;
import java.util.List;

@Controller
@Scope("prototype")
public class SubareaAction extends BaseAction<Subarea> {
    @Autowired
    private ISubareaService subareaService;

    /**
     * 分页查询
     *
     * @return
     * @throws Exception
     */
    public String pageQuery() throws Exception {
        //动态添加过滤条件
        DetachedCriteria detachedCriteria = pageBean.getDetachedCriteria();
        String addresskey = model.getAddresskey();
        if (StringUtils.isNotBlank(addresskey)) {
            detachedCriteria.add(Restrictions.like("addresskey", "%" + addresskey + "%"));
        }
        Region region = model.getRegion();
        if (region != null) {
            String city = region.getCity();
            String province = region.getProvince();
            String district = region.getDistrict();

            detachedCriteria.createAlias("region", "r");//查询关联表，创建别名
            if (StringUtils.isNotBlank(city)) {
                detachedCriteria.add(Restrictions.like("r.city", "%" + city + "%"));
            }

            if (StringUtils.isNotBlank(province)) {
                detachedCriteria.add(Restrictions.like("r.province", "%" + province + "%"));
            }
            if (StringUtils.isNotBlank(district)) {
                detachedCriteria.add(Restrictions.like("r.district", "%" + district + "%"));
            }
        }
        subareaService.pageQuery(pageBean);
        this.objectConvertJson(pageBean, new String[]{"currentPage","detachedCriteria","pageSize",
                "decidedzone","subareas"});
        return NONE;
    }

    /**
     * 添加数据
     *
     * @return
     * @throws Exception
     */
    public String add() throws Exception {
        subareaService.save(model);
        return LIST;
    }

    /**
     * 下载xls文件
     *
     * @return
     * @throws Exception
     */
    public String exportXls() throws Exception {
        //查询所有分区的数据
        List<Subarea> list = subareaService.findAll();

        //使用poi，将数据写如excel中
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        //创建一个标签页
        HSSFSheet sheet = hssfWorkbook.createSheet("分区数据");

        //创建标题行
        HSSFRow headRow = sheet.createRow(0);
        headRow.createCell(0).setCellValue("分区编号");
        headRow.createCell(1).setCellValue("开始编号");
        headRow.createCell(2).setCellValue("结束编号");
        headRow.createCell(3).setCellValue("位置信息");
        headRow.createCell(4).setCellValue("省市区");

        for (Subarea subarea : list) {
            HSSFRow dataRow = sheet.createRow(sheet.getLastRowNum() + 1);
            dataRow.createCell(0).setCellValue(subarea.getId());
            dataRow.createCell(1).setCellValue(subarea.getStartnum());
            dataRow.createCell(2).setCellValue(subarea.getEndnum());
            dataRow.createCell(3).setCellValue(subarea.getPosition());
            dataRow.createCell(4).setCellValue(subarea.getRegion().getName());
        }

        //第三步：使用输出流进行文件下载（一个流、两个头）
        String fileName = "分区数据.xls";
        //通过文件名，判断文件类型
        String mimeType = ServletActionContext.getServletContext().getMimeType(fileName);
        ServletOutputStream outputStream = ServletActionContext.getResponse().getOutputStream();
        ServletActionContext.getResponse().setContentType(mimeType);
        //获取客户端浏览器类型
        String agent = ServletActionContext.getRequest().getHeader("User-Agent");
        fileName = FileUtils.encodeDownloadFilename(fileName, agent);
        ServletActionContext.getResponse().setHeader("content-disposition", "attachment;filename=" + fileName);

        hssfWorkbook.write(outputStream);
        return NONE;
    }

    /**
     * 查询所有未关联定区的分区
     *
     * @return
     */
    public String listAjax() {
        List<Subarea> list = subareaService.findListNotAssociation();
        this.objectConvertJson(list, new String[]{"decidedzone","region"});
        return NONE;
    }
}
