package com.mariusar.demospring.rest.mapper;

import com.mariusar.demospring.rest.model.App;
import com.mariusar.demospring.rest.model.AppVo;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-29T16:07:54+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class AppMapperImpl implements AppMapper {

    @Override
    public AppVo toVo(App app) {
        if ( app == null ) {
            return null;
        }

        AppVo appVo = new AppVo();

        appVo.setCode( app.getCode() );
        appVo.setDescription( app.getDescription() );

        return appVo;
    }

    @Override
    public App toDomain(AppVo appVo) {
        if ( appVo == null ) {
            return null;
        }

        App app = new App();

        app.setCode( appVo.getCode() );
        app.setDescription( appVo.getDescription() );

        return app;
    }
}
