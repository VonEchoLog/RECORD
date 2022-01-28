package com.record.recordmvc.utils;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author FBY
 * @version 1.0
 * @date 2022-1-26 10:32
 * @Description
 */
public class SqlBatchUpdate {
    public static void main(String[] args) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/record?characterEncoding=UTF-8");
        dataSource.setUsername("root");
        dataSource.setPassword("root");

        String sql = "select * from SG_DEV_FEEDERLINE_H1_MEA_2021";
        JdbcTemplate jdbcTemplate =new JdbcTemplate(dataSource);
        List<Map<String,Object>> datas =jdbcTemplate.queryForList(sql);
       //List<Map<String,Object>> datas = DBTemplate.getInstance().getResultMapList(sql);
        for(Map<String,Object> item : datas){
            List<Double> ratios =new ArrayList<>();
            for(int i=0;i<60;i++){
                double random =Math.random();
                int j =new Double(random*10000).intValue();
                if(j%2==0){
                    random=1-random*5/10;
                }else{
                    random=1+random*5/10;
                }
                String randomStr=String.format("%.4f",random);
                double ratio =Double.parseDouble(randomStr);
                ratios.add(ratio);
            }

            String result="UPDATE SG_DEV_FEEDERLINE_H1_MEA_2021 SET "+
                    "V00 ="+Double.parseDouble(item.get("V00").toString())* ratios.get(0 )+","+
                    "V01 ="+Double.parseDouble(item.get("V01").toString())* ratios.get(1 )+","+
                    "V02 ="+Double.parseDouble(item.get("V02").toString())* ratios.get(2 )+","+
                    "V03 ="+Double.parseDouble(item.get("V03").toString())* ratios.get(3 )+","+
                    "V04 ="+Double.parseDouble(item.get("V04").toString())* ratios.get(4 )+","+
                    "V05 ="+Double.parseDouble(item.get("V05").toString())* ratios.get(5 )+","+
                    "V06 ="+Double.parseDouble(item.get("V06").toString())* ratios.get(6 )+","+
                    "V07 ="+Double.parseDouble(item.get("V07").toString())* ratios.get(7 )+","+
                    "V08 ="+Double.parseDouble(item.get("V08").toString())* ratios.get(8 )+","+
                    "V09 ="+Double.parseDouble(item.get("V09").toString())* ratios.get(9 )+","+
                    "V10 ="+Double.parseDouble(item.get("V10").toString())* ratios.get(10)+","+
                    "V11 ="+Double.parseDouble(item.get("V11").toString())* ratios.get(11)+","+
                    "V12 ="+Double.parseDouble(item.get("V12").toString())* ratios.get(12)+","+
                    "V13 ="+Double.parseDouble(item.get("V13").toString())* ratios.get(13)+","+
                    "V14 ="+Double.parseDouble(item.get("V14").toString())* ratios.get(14)+","+
                    "V15 ="+Double.parseDouble(item.get("V15").toString())* ratios.get(15)+","+
                    "V16 ="+Double.parseDouble(item.get("V16").toString())* ratios.get(16)+","+
                    "V17 ="+Double.parseDouble(item.get("V17").toString())* ratios.get(17)+","+
                    "V18 ="+Double.parseDouble(item.get("V18").toString())* ratios.get(18)+","+
                    "V19 ="+Double.parseDouble(item.get("V19").toString())* ratios.get(19)+","+
                    "V20 ="+Double.parseDouble(item.get("V20").toString())* ratios.get(20)+","+
                    "V21 ="+Double.parseDouble(item.get("V21").toString())* ratios.get(21)+","+
                    "V22 ="+Double.parseDouble(item.get("V22").toString())* ratios.get(22)+","+
                    "V23 ="+Double.parseDouble(item.get("V23").toString())* ratios.get(23)+","+
                    "V24 ="+Double.parseDouble(item.get("V24").toString())* ratios.get(24)+","+
                    "V25 ="+Double.parseDouble(item.get("V25").toString())* ratios.get(25)+","+
                    "V26 ="+Double.parseDouble(item.get("V26").toString())* ratios.get(26)+","+
                    "V27 ="+Double.parseDouble(item.get("V27").toString())* ratios.get(27)+","+
                    "V28 ="+Double.parseDouble(item.get("V28").toString())* ratios.get(28)+","+
                    "V29 ="+Double.parseDouble(item.get("V29").toString())* ratios.get(29)+","+
                    "V30 ="+Double.parseDouble(item.get("V30").toString())* ratios.get(30)+","+
                    "V31 ="+Double.parseDouble(item.get("V31").toString())* ratios.get(31)+","+
                    "V32 ="+Double.parseDouble(item.get("V32").toString())* ratios.get(32)+","+
                    "V33 ="+Double.parseDouble(item.get("V33").toString())* ratios.get(33)+","+
                    "V34 ="+Double.parseDouble(item.get("V34").toString())* ratios.get(34)+","+
                    "V35 ="+Double.parseDouble(item.get("V35").toString())* ratios.get(35)+","+
                    "V36 ="+Double.parseDouble(item.get("V36").toString())* ratios.get(36)+","+
                    "V37 ="+Double.parseDouble(item.get("V37").toString())* ratios.get(37)+","+
                    "V38 ="+Double.parseDouble(item.get("V38").toString())* ratios.get(38)+","+
                    "V39 ="+Double.parseDouble(item.get("V39").toString())* ratios.get(39)+","+
                    "V40 ="+Double.parseDouble(item.get("V40").toString())* ratios.get(40)+","+
                    "V41 ="+Double.parseDouble(item.get("V41").toString())* ratios.get(41)+","+
                    "V42 ="+Double.parseDouble(item.get("V42").toString())* ratios.get(42)+","+
                    "V43 ="+Double.parseDouble(item.get("V43").toString())* ratios.get(43)+","+
                    "V44 ="+Double.parseDouble(item.get("V44").toString())* ratios.get(44)+","+
                    "V45 ="+Double.parseDouble(item.get("V45").toString())* ratios.get(45)+","+
                    "V46 ="+Double.parseDouble(item.get("V46").toString())* ratios.get(46)+","+
                    "V47 ="+Double.parseDouble(item.get("V47").toString())* ratios.get(47)+","+
                    "V48 ="+Double.parseDouble(item.get("V48").toString())* ratios.get(48)+","+
                    "V49 ="+Double.parseDouble(item.get("V49").toString())* ratios.get(49)+","+
                    "V50 ="+Double.parseDouble(item.get("V50").toString())* ratios.get(50)+","+
                    "V51 ="+Double.parseDouble(item.get("V51").toString())* ratios.get(51)+","+
                    "V52 ="+Double.parseDouble(item.get("V52").toString())* ratios.get(52)+","+
                    "V53 ="+Double.parseDouble(item.get("V53").toString())* ratios.get(53)+","+
                    "V54 ="+Double.parseDouble(item.get("V54").toString())* ratios.get(54)+","+
                    "V55 ="+Double.parseDouble(item.get("V55").toString())* ratios.get(55)+","+
                    "V56 ="+Double.parseDouble(item.get("V56").toString())* ratios.get(56)+","+
                    "V57 ="+Double.parseDouble(item.get("V57").toString())* ratios.get(57)+","+
                    "V58 ="+Double.parseDouble(item.get("V58").toString())* ratios.get(58)+","+
                    "V59 ="+Double.parseDouble(item.get("V59").toString())* ratios.get(59)+

                    " WHERE ID ='"+item.get("ID").toString() +"' and MEAS_TYPE ='"+item.get("MEAS_TYPE").toString()+"' and CREATE_TIME = '"+item.get("CREATE_TIME").toString()+"';";

            System.out.println(result);

        }

    }
}
