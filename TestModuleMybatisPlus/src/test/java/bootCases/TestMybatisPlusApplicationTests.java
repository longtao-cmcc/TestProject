package bootCases;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.DemoApplication;
import com.demo.mappers.AdVersionMngMapper;
import com.demo.model.AdVersionMng;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class TestMybatisPlusApplicationTests {

    @Autowired
    private AdVersionMngMapper versionMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<AdVersionMng> versionList = versionMapper.selectList(null);
        for(AdVersionMng version:versionList) {
            System.out.println("Get mng log:" + version.getId());
        }
    }

    @Test
    public void testOthers()
    {
        AdVersionMng version = versionMapper.selectById(1000);

        System.out.println("Get version:" + version.getName() + version.getId());

        List<Integer> paramList = Arrays.asList(1000,1001,10002,10000001);

        List<AdVersionMng> versionList = versionMapper.selectBatchIds(paramList);
        for(AdVersionMng tmpVersion:versionList) {
            System.out.println("Get mng log:" + tmpVersion.getId());
        }

        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("id",10000001);
        paramMap.put("name","Tower123");

        versionList = versionMapper.selectByMap(paramMap);
        for(AdVersionMng tmpVersion:versionList) {
            System.out.println("Get selectByMap result:" + tmpVersion.getId());
        }

        return;

    }

    @Test
    public void mybatisPlusSelectList()
    {
        QueryWrapper<AdVersionMng> queryWrapper = new QueryWrapper<AdVersionMng>();

        queryWrapper.like("name","123");
        //queryWrapper.like("name","123").gt("id",10000002);
        //queryWrapper.likeLeft("name","123").or().orderByDesc("id");


        List<AdVersionMng> versionList = versionMapper.selectList(queryWrapper);
        for(AdVersionMng tmpVersion:versionList) {
            System.out.println("Get selectList result:" + tmpVersion.getId());
        }
        versionList.forEach(System.out::println);

        queryWrapper.clear();
        queryWrapper.like("name","123");
        System.out.println("Get " + versionMapper.selectCount(queryWrapper) + " msg");

    }

    @Test
    public void mybatisPlusSelectPage()
    {
        QueryWrapper<AdVersionMng> versionWrap = new QueryWrapper<AdVersionMng>();
        versionWrap.like("name","Tower");

        Page<AdVersionMng> page = new Page<AdVersionMng>(3,5);

        IPage<AdVersionMng> pageResult = versionMapper.selectPage(page,versionWrap);
        long iCount = pageResult.getSize();
        long iSize = pageResult.getPages();

        System.out.println("Size = " + iCount + ",Pages = " + iSize);

        List<AdVersionMng> versionList = pageResult.getRecords();
        for(AdVersionMng tmpVersion:versionList) {
            System.out.println("Get selectList result1:" + tmpVersion.getId());
        }

        pageResult = versionMapper.selectPage(new Page<AdVersionMng>(2,5),versionWrap);
        versionList = pageResult.getRecords();
        for(AdVersionMng tmpVersion:versionList) {
            System.out.println("Get selectList result2:" + tmpVersion.getId());
        }

    }

    @Test
    public void mybatisPlusUpdateById()
    {
        AdVersionMng newVersion = new AdVersionMng();

        newVersion.setId(10000001);
        newVersion.setName("TowerNew");
        newVersion.setVersion(9999);

        int iRet = versionMapper.updateById(newVersion);
        System.out.println("updateById result:" + iRet);

        UpdateWrapper<AdVersionMng> updateWrapper = new UpdateWrapper<AdVersionMng>();
        updateWrapper.like("name","123");
        newVersion.setId(2222);
        newVersion.setName("Tower12345");
        newVersion.setVersion(1111);

        iRet = versionMapper.update(newVersion,updateWrapper);
        System.out.println("updateByWrap result:" + iRet);


    }

    @Test
    public void mybatisPlusDeleteById()
    {
        AdVersionMng newVersion = new AdVersionMng();

        newVersion.setId(10000001);
        newVersion.setName("TowerNew");
        newVersion.setVersion(9999);

        int iRet = versionMapper.deleteById(10000010);
        System.out.println("deleteById result:" + iRet);

        iRet = versionMapper.deleteBatchIds(Arrays.asList(10000011,10000012,10000013,10000014));
        System.out.println("deleteById result:" + iRet);

        LambdaQueryWrapper<AdVersionMng> lambdaQueryWrapper = new LambdaQueryWrapper<AdVersionMng>();
        lambdaQueryWrapper.gt(AdVersionMng::getId,10001000);
        iRet = versionMapper.delete(lambdaQueryWrapper);
        System.out.println("deleteById result:" + iRet);

    }

}