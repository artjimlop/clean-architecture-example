/**
 * Copyright (C) 2016 Arturo Open Source Project
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.artjimlop.data.bean.mapper;

import com.example.artjimlop.data.bean.ComicResponseDto;
import com.example.bean.Comic;

public final class ComicResponseDtoMapper {

    private ComicResponseDtoMapper() {
    }

    public static Comic toBusinessObject(ComicResponseDto dto) {
        Comic businessObject = null;
        if (dto != null) {
            businessObject = new Comic();
            businessObject.setId(dto.getId());
            businessObject.setDescription(dto.getDescription());
            businessObject.setPageCount(dto.getPageCount());
            businessObject.setTitle(dto.getTitle());
            businessObject.setImageUrls(ImageResponseDtoMapper.toString(dto.getImages()));
            businessObject.setThumbnailUrl(ImageResponseDtoMapper.toString(dto.getThumbnail()));
        }
        return businessObject;
    }
}
