package com.example.demo.list_stream;


import com.example.demo.list_stream.vo.StreamBean;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: List的stream()的用法
 * @Author: shizile
 * @Date: 2021/5/27 19:14
 **/
public class Test {
    public static void main(String[] args) {
        Test streamTest = new Test();

        // 初始化测试数据.
        List<StreamBean> streamBeanList = streamTest.initList();

//        System.out.println("初始化List如下：");
//        // 控制台循环打印初始化数据.
//        streamBeanList.forEach(iten -> System.out.println(iten.getName() + "," + iten.getSex() + "," + iten.getAge()));
//
        System.out.println("\nFilter测试（查询list性别为女的所有数据）：");
        streamTest.testFilter(streamBeanList, "女");
//
//        System.out.println("\nSorted测试：");
//        streamTest.testSorted(streamBeanList);
//
//        System.out.println("\nMap测试：");
//        streamTest.testMap(streamBeanList);

//        System.out.println("\nCollectors测试：");
//        streamTest.testCollectors(streamBeanList);

//        System.out.println("\nStatistics测试：");
//        streamTest.testStatistics(streamBeanList);
    }

    /**
     * Filter测试.
     *
     * @param sex 根据性别筛选.
     */
    public void testFilter(List<StreamBean> streamBeanList, String sex) {
        if (CollectionUtils.isNotEmpty(streamBeanList)) {
            List<StreamBean> result = streamBeanList.stream().filter(item -> sex.equals(item.getSex())).collect(Collectors.toList());
            List<Integer> ids = streamBeanList.stream().map(StreamBean::getAge).collect(Collectors.toList());
            result.forEach(item -> System.out.println(item.getName() + "," + item.getSex() + "," + item.getAge()));
            ids.forEach(System.out::println);
        }
    }

    /**
     * 测试排序.
     *
     * @param streamBeanList
     */
    public void testSorted(List<StreamBean> streamBeanList) {

        if (CollectionUtils.isNotEmpty(streamBeanList)) {
            System.out.println("按性别排序");
            List<StreamBean> result = streamBeanList.stream().sorted(Comparator.comparing(StreamBean::getSex)).collect(Collectors.toList());
            result.stream().forEach(item -> System.out.println(item.getName() + " | " + item.getSex() + " | " + item.getAge()));

            System.out.println("按性别排序2");
            List<StreamBean> result2 = streamBeanList.stream().sorted(Comparator.comparing(StreamBean::getSex).reversed()).collect(Collectors.toList());
            result2.stream().forEach(item -> System.out.println(item.getName() + " | " + item.getSex() + " | " + item.getAge()));

            System.out.println("按年龄排序1");

            List<StreamBean> result3 = streamBeanList.stream().sorted(Comparator.comparing(StreamBean::getAge)).collect(Collectors.toList());

            result3.stream().forEach(item -> System.out.println(item.getName() + " | " + item.getSex() + " | " + item.getAge()));

            System.out.println("按年龄排序2");

            List<StreamBean> result4 = streamBeanList.stream().sorted(Comparator.comparing(StreamBean::getAge).reversed()).collect(Collectors.toList());

            result4.stream().forEach(item -> System.out.println(item.getName() + " | " + item.getSex() + " | " + item.getAge()));
        }
    }


    public void testMap(List<StreamBean> streamBeanList) {

        // 通过map抽取bean列表的所有姓名
        if (CollectionUtils.isNotEmpty(streamBeanList)) {
            System.out.println("抽取列表实体所有姓名：");

            List<String> collect = streamBeanList.stream().map(item -> item.getName()).collect(Collectors.toList());
            collect.stream().forEach(item -> System.out.println(item));

            System.out.println("将列表所有姓名添加前缀A_：");

            List<StreamBean> collect1 = streamBeanList.stream().map(item -> {
                item.setName("A_" + item.getName());
                return item;
            }).sorted(Comparator.comparing(StreamBean::getSex)).collect(Collectors.toList());

            collect1.stream().forEach(item -> System.out.println(item.getName() + " | " + item.getSex()));
        }
    }

    /**
     * Collectors过滤抽取合并字符串测试.
     *
     * @param streamBeanList
     */
    public void testCollectors(List<StreamBean> streamBeanList) {

        if (CollectionUtils.isNotEmpty(streamBeanList)) {

            // 所有男性的姓名合并字符串用逗号分隔.
            String collect = streamBeanList.stream().filter(item -> "男".equals(item.getSex())).map(item -> item.getName()).collect(Collectors.joining(","));
            String collect2 = streamBeanList.stream().map(item -> item.getName()).collect(Collectors.joining(","));
            System.out.println(collect);
            System.out.println(collect2);
        }

    }


    /**
     * Statistics测试.
     *
     * @param streamBeanList
     */
    public void testStatistics(List<StreamBean> streamBeanList) {

        IntSummaryStatistics intSummaryStatistics = streamBeanList.stream().mapToInt(item -> item.getAge()).summaryStatistics();

        System.out.println("年龄最大的是：" + intSummaryStatistics.getMax());
        System.out.println("年龄最小的是：" + intSummaryStatistics.getMin());
        System.out.println("年龄总和是：" + intSummaryStatistics.getSum());
        System.out.println("平均年龄是：" + intSummaryStatistics.getAverage());
        System.out.println("人数总和是：" + intSummaryStatistics.getCount());


    }


    /**
     * 初始化数据.
     *
     * @return List<StreamBean>.
     */
    public List<StreamBean> initList() {

        List<StreamBean> streamBeanList = new ArrayList<StreamBean>();

        StreamBean bean1 = new StreamBean();
        bean1.setName("刘某");
        bean1.setSex("男");
        bean1.setAge(66);

        StreamBean bean2 = new StreamBean();
        bean2.setName("张某");
        bean2.setSex("女");
        bean2.setAge(55);

        StreamBean bean3 = new StreamBean();
        bean3.setName("王某");
        bean3.setSex("男");
        bean3.setAge(58);

        StreamBean bean4 = new StreamBean();
        bean4.setName("梁某");
        bean4.setSex("女");
        bean4.setAge(44);

        StreamBean bean5 = new StreamBean();
        bean5.setName("周某");
        bean5.setSex("男");
        bean5.setAge(43);

        streamBeanList.add(bean1);
        streamBeanList.add(bean2);
        streamBeanList.add(bean3);
        streamBeanList.add(bean4);
        streamBeanList.add(bean5);

        return streamBeanList;
    }

}

