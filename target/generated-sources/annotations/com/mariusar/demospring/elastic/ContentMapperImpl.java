package com.mariusar.demospring.elastic;

import com.mariusar.demospring.elastic.content.AppContent;
import com.mariusar.demospring.rest.model.AppVo;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-29T16:07:54+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class ContentMapperImpl implements ContentMapper {

    @Override
    public AppVo toVo(AppContent content) {
        if ( content == null ) {
            return null;
        }

        AppVo appVo = new AppVo();

        appVo.setCode( content.getCode() );
        appVo.setDescription( content.getDescription() );

        return appVo;
    }

    @Override
    public List<AppVo> toVos(List<AppContent> contents) {
        if ( contents == null ) {
            return null;
        }

        List<AppVo> list = new ArrayList<AppVo>( contents.size() );
        for ( AppContent appContent : contents ) {
            list.add( toVo( appContent ) );
        }

        return list;
    }
}
