package com.dpubleDimple.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.dpubleDimple.entity.enums.PageSize;
import com.dpubleDimple.entity.page.PaginationResult;
import com.dpubleDimple.entity.page.SimplePage;
import com.dpubleDimple.entity.pojo.Price;
import com.dpubleDimple.entity.query.PriceQuery;
import com.dpubleDimple.mapper.dao2.PriceMapper;
import com.dpubleDimple.service.PriceService;
import org.springframework.stereotype.Service;


/**
 * 
 *  业务接口实现
 * 
 */
@Service("priceService")
public class PriceServiceImpl implements PriceService {

	@Resource
	private PriceMapper<Price, PriceQuery> priceMapper;

	/**
	 * 根据条件查询列表
	 */
	public List<Price> findListByParam(PriceQuery param) {
		List<Price> list = this.priceMapper.selectList(param);
		return list;
	}

	/**
	 * 根据条件查询列表
	 */
	public Integer findCountByParam(PriceQuery param) {
		Integer count = this.priceMapper.selectCount(param);
		return count;
	}

	/**
	 * 分页查询方法
	 */
	public PaginationResult<Price> findListByPage(PriceQuery param) {
		int count = this.priceMapper.selectCount(param);
		int pageSize = param.getPageSize()==null? PageSize.SIZE15.getSize():param.getPageSize();
		int pageNo = 0;
		if (null != param.getPageNo()) {
			pageNo=param.getPageNo();
		}
		SimplePage page = new SimplePage(pageNo, count, pageSize);
		param.setPage(page);
		List<Price> list = this.priceMapper.selectList(param);
		PaginationResult<Price> result = new PaginationResult<Price>(page, list);
		return result;
	}

	/**
	 * 新增
	 */
	public Integer add(Price bean){
		return this.priceMapper.insert(bean);
	}

	/**
	 * 批量新增
	 */
	public Integer addBatch(List<Price> listBean){
		if (listBean == null || listBean.isEmpty()) {
			return 0;
		}
		return this.priceMapper.insertBatch(listBean);
	}

	/**
	 * 修改
	 */
	public Integer updateByPrimaryKey(Price bean,Long id){
		return this.priceMapper.updateByPrimaryKey(bean,id);
	}

	/**
	 * 删除
	 */
	public Integer deleteByPrimaryKey(Long id){
		return this.priceMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 根据primaryKey获取对象
	 */
	public Price getPriceByPrimaryKey(Long id){
		return this.priceMapper.selectByPrimaryKey(id);
	}

}