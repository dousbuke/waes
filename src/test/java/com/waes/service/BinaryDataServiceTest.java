package com.waes.service;

import com.waes.domain.api.BinaryDataRequest;
import com.waes.domain.dto.BinaryDataSummaryDto;
import com.waes.domain.entity.BinaryData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class BinaryDataServiceTest {

    @Autowired
    private BinaryDataService binaryDataService;

    @Test
    public void record() {
        BinaryDataRequest binaryDataRequest = BinaryDataRequest.builder()
                .binaryData("gfbgfbfbfnhsfdvfdgbfgb")
                .side("LEFT")
                .build();

        BinaryData result = binaryDataService.record("2", binaryDataRequest);

        assertThat(result.getId(), equalTo(1L));
        assertThat(result.getSide(), equalTo("LEFT"));
        assertThat(result.getRecordId(), equalTo("2"));
        assertThat(result.getRecord(), equalTo("gfbgfbfbfnhsfdvfdgbfgb"));
    }

    @Test
    public void save() {
        BinaryData binaryData = BinaryData.builder()
                .record("fdvdfd;lnknvkfnvfdhvb-vvvrtvrbgbfbwdcdc")
                .recordId("1")
                .side("LEFT")
                .createdDate(LocalDateTime.of(2020, 11, 2, 18, 30, 0))
                .build();

        BinaryData result = binaryDataService.save(binaryData);

        assertThat(result.getId(), equalTo(1L));
        assertThat(result.getSide(), equalTo("LEFT"));
        assertThat(result.getRecordId(), equalTo("1"));
        assertThat(result.getRecord(), equalTo("fdvdfd;lnknvkfnvfdhvb-vvvrtvrbgbfbwdcdc"));
        assertThat(result.getCreatedDate().toString(), equalTo("2020-11-02T18:30"));
    }

    @Test
    public void getSummary() {
        BinaryData binaryData = BinaryData.builder()
                .record("fdvdfd;lnknvkfnvfdhvb-vvvrtvrbgbfbwdcdc")
                .recordId("1")
                .side("LEFT")
                .createdDate(LocalDateTime.of(2020, 11, 2, 18, 30, 0))
                .build();

        binaryDataService.save(binaryData);
        BinaryData binaryData2 = BinaryData.builder()
                .record("fdvdfd;lnknvkfnvfdhvb-vvvrtvrbgbfbwdcdcvfvfvfvdf")
                .recordId("1")
                .side("RIGHT")
                .createdDate(LocalDateTime.of(2020, 11, 2, 18, 31, 0))
                .build();
        binaryDataService.save(binaryData2);

        BinaryDataSummaryDto result = binaryDataService.getSummary("1");

        assertThat(result.getResult(), equalTo("RIGHT"));
        assertThat(result.getDifference(), equalTo(9));
        assertThat(result.getLeftRecordSize(), equalTo(39));
        assertThat(result.getRightRecordSize(), equalTo(48));
    }

    @Test
    public void getAllByRecordIdAndSide() {
        BinaryData binaryData = BinaryData.builder()
                .record("fdvdfd;lnknvkfnvfdhvb-vvvrtvrbgbfbwdcdc")
                .recordId("1")
                .side("LEFT")
                .createdDate(LocalDateTime.of(2020, 11, 2, 18, 30, 0))
                .build();
        binaryDataService.save(binaryData);

        Optional<BinaryData> result = binaryDataService.getAllByRecordIdAndSide("1", "LEFT");

        assertThat(result.isPresent(), equalTo(true));
        assertThat(result.get().getId(), equalTo(1L));
        assertThat(result.get().getSide(), equalTo("LEFT"));
        assertThat(result.get().getRecordId(), equalTo("1"));
        assertThat(result.get().getRecord(), equalTo("fdvdfd;lnknvkfnvfdhvb-vvvrtvrbgbfbwdcdc"));
        assertThat(result.get().getCreatedDate().toString(), equalTo("2020-11-02T18:30"));
    }

    @Test
    public void getAllByRecordId() {
        BinaryData binaryData = BinaryData.builder()
                .record("fdvdfd;lnknvkfnvfdhvb-vvvrtvrbgbfbwdcdc")
                .recordId("1")
                .side("LEFT")
                .createdDate(LocalDateTime.of(2020, 11, 2, 18, 30, 0))
                .build();

        binaryDataService.save(binaryData);
        BinaryData binaryData2 = BinaryData.builder()
                .record("fdvdfd;lnknvkfnvfdhvb-vvvrtvrbgbfbwdcdcvfvfvfvdf")
                .recordId("1")
                .side("RIGHT")
                .createdDate(LocalDateTime.of(2020, 11, 2, 18, 31, 0))
                .build();
        binaryDataService.save(binaryData2);

        List<BinaryData> result = binaryDataService.getAllByRecordId("1");

        assertThat(result.get(0).getId(), equalTo(1L));
        assertThat(result.get(0).getSide(), equalTo("LEFT"));
        assertThat(result.get(0).getRecordId(), equalTo("1"));
        assertThat(result.get(0).getRecord(), equalTo("fdvdfd;lnknvkfnvfdhvb-vvvrtvrbgbfbwdcdc"));
        assertThat(result.get(0).getCreatedDate().toString(), equalTo("2020-11-02T18:30"));

        assertThat(result.get(1).getId(), equalTo(2L));
        assertThat(result.get(1).getSide(), equalTo("RIGHT"));
        assertThat(result.get(1).getRecordId(), equalTo("1"));
        assertThat(result.get(1).getRecord(), equalTo("fdvdfd;lnknvkfnvfdhvb-vvvrtvrbgbfbwdcdcvfvfvfvdf"));
        assertThat(result.get(1).getCreatedDate().toString(), equalTo("2020-11-02T18:31"));
    }

}