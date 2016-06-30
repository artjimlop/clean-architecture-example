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
import com.example.artjimlop.data.bean.ComicsResponseDto;
import com.example.artjimlop.data.bean.ListResponseDto;
import com.example.bean.Comic;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public final class ComicsResponseDtoMapper {

    @Inject public ComicsResponseDtoMapper() {
    }

    public List<Comic> toBusinessObjects(ComicsResponseDto dtos) {
        List<Comic> businessObjects = null;
        if (dtos != null) {
            ListResponseDto<ComicResponseDto> data = dtos.getData();
            if (data != null) {
                List<ComicResponseDto> results = data.getResults();
                if (results != null && !results.isEmpty()) {
                    businessObjects = new ArrayList<>();
                    for (ComicResponseDto dto : results) {
                        businessObjects.add(ComicResponseDtoMapper.toBusinessObject(dto));
                    }
                }
            }
        }
        return businessObjects;
    }
}
