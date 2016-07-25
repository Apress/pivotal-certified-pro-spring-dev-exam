package com.ps.config.db;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by iuliana.cosmina on 7/25/16.
 */
public interface DataConfig {
    DataSource dataSource();

    Properties hibernateProperties();
}
