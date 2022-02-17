package com.example.demoapp1;

import com.example.demoapp1.model.BusinessProcess;
import com.example.demoapp1.model.BusinessProcessState;
import com.example.demoapp1.service.BusinessProcessRepository;
import com.example.demoapp1.service.BusinessProcessStateRepository;
import com.example.demoapp1.workflow.core.WorkflowTaskManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@SpringBootTest
class Demoapp1ApplicationTests {

	@Autowired
	BusinessProcessRepository businessProcessRepository;

	@Autowired
	BusinessProcessStateRepository businessProcessStateRepository;

	@Autowired
	WorkflowTaskManager workflowTaskManager;

	@Test
	void contextLoads() {
	}

	@Test
	void testCreateWorkflow() {
		BusinessProcess bp = new BusinessProcess();
		bp.setBusinessProcessTypeCode("Origination");
		bp.setBusinessProcessStatus("Pending");
		bp.setAppEventTypeCode("CreateWorkflow");
		bp.setTransactTs(new Date());
		bp.setUserId("naikravi");

		businessProcessRepository.save(bp);

		BusinessProcessState bps = new BusinessProcessState();
		bps.setAppEventTypeCode("CreateWorkflow");
		bps.setBusinessProcessId(bp.getBusinessProcessId());
		bps.setBusinessProcessStepCode("PrimaryOpsReview");
		bps.setBusinessProcessStateCode("Pending");
		bps.setTransactTs(new Date());
		bps.setUserId("naikravi");

		businessProcessStateRepository.save(bps);
	}

	@Autowired
	void testApplyEvent() {
		List<BusinessProcessState> bpsList = businessProcessStateRepository.findByBusinessProcessStepCodeAndBusinessProcessStateCode("PrimaryOpsReview", "Pending");
		if (bpsList != null && bpsList.size() > 0) {
			Map<Integer, List<BusinessProcessState>> businessProcessStateMap = bpsList.stream().collect(
					Collectors.groupingBy(BusinessProcessState::getBusinessProcessId,
						Collectors.mapping(Function.identity(), Collectors.toList())));

			int processId = (int)businessProcessStateMap.keySet().toArray()[0];

			List<BusinessProcessState> processStateList = businessProcessStateMap.get(processId);
			List<String> startingState = new ArrayList();

			for (BusinessProcessState bps: processStateList
				 ) {
				startingState.add(bps.getBusinessProcessStepCode() + "-" + bps.getBusinessProcessStateCode());
			}

			String startingStatesArray[] = new String[startingState.size()];
			startingState.toArray(startingStatesArray);

			List<String> targetStateList = workflowTaskManager.applyEvent(startingStatesArray, "PrimaryOpsReviewApprove");

			for (String stepState: targetStateList
				 ) {
				String[] stepStateArray = stepState.split("-");

				BusinessProcessState bps = new BusinessProcessState();
				bps.setAppEventTypeCode("PrimaryOpsReviewApprove");
				bps.setBusinessProcessId(processId);
				bps.setBusinessProcessStepCode(stepStateArray[0]);
				bps.setBusinessProcessStateCode(stepStateArray[1]);
				bps.setTransactTs(new Date());
				bps.setUserId("naikravi");
				businessProcessStateRepository.save(bps);
			}

			for (BusinessProcessState bps: processStateList
			) {
				businessProcessStateRepository.delete(bps);
			}

		}
	}

}
