package com.taotao.service;

import com.common.pojo.PictureResult;

public interface PictureService {
	public PictureResult uploadFile(byte[] bytes, String name);
}
