package com.demo.Visitor.access.service;

import com.demo.Visitor.access.dto.RegisterUserDto;
import com.demo.Visitor.access.exception.BusinessException;
import com.demo.Visitor.access.model.ODCList;
import com.demo.Visitor.access.model.UserInfo;
import com.demo.Visitor.access.model.VisitorRequest;
import com.demo.Visitor.access.repository.ODCRepository;
import com.demo.Visitor.access.repository.UserRepository;
import com.demo.Visitor.access.repository.VisitorRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	ODCRepository odcRepository;

	@Autowired
	VisitorRequestRepository visitorRequestRepository;

	@Autowired
	BCryptPasswordEncoder bcryptPasswordEncoder;

	public boolean register(RegisterUserDto registerUserDto) {
		registerUserDto.password = bcryptPasswordEncoder.encode(registerUserDto.password);
		UserInfo userInfo = new UserInfo(registerUserDto);
		Optional<UserInfo> user = userRepository.findByEmpId(registerUserDto.empId);
		if (user.isPresent())
			return false;
		userInfo.setAccountActive(false);
		userRepository.save(userInfo);
		return true;
	}

	public UserInfo login(int empId, String password) throws BusinessException {
		Optional<UserInfo> user = userRepository.findByEmpId(empId);
		if (user.isPresent()) {
			if (user.get().getEmpId() == empId) {
				if (bcryptPasswordEncoder.matches(password, user.get().getPassword())) {
					if (user.get().getAccountActive()) {
						return user.get();
					}
					throw new BusinessException("Registration request not approved");
				}
				throw new BusinessException("Invalid credentials..");
			}
		}
		throw new BusinessException("Account not exist");
	}

	public List<UserInfo> getAllUserData() {
		List<UserInfo> userList = userRepository.findAll();
		userList.removeIf(value -> value.getRole().equals("Admin"));
		return userList;
	}

	public void deleteUser(int empId) {
		userRepository.deleteByEmpId(empId);
	}

	public boolean insertIntoVisitorRequest(VisitorRequest visitorRequest) throws BusinessException {
		VisitorRequest visitorRequestAdded = visitorRequestRepository.insert(visitorRequest);
		if (visitorRequestAdded == null)
			throw new BusinessException("Something went wrong!!!Please try again");
		else
			return true;
	}

	public List<VisitorRequest> findAllByEmpId(int empId) throws BusinessException {
		List<VisitorRequest> visitorRequests = visitorRequestRepository.findAllByEmpId(empId);
		if (visitorRequests == null)
			throw new BusinessException("No requests has been raised for this employee Id");
		else
			return visitorRequests;
	}

	public List<ODCList> findAllODC() throws BusinessException {
		List<ODCList> odcLists = odcRepository.findAll();
		if (odcLists == null)
			throw new BusinessException("No ODC is present");
		else
			return odcLists;
	}

	public boolean addOdc(ODCList odc) throws BusinessException {
		ODCList odcAdded = odcRepository.insert(odc);
		if (odcAdded == null)
			throw new BusinessException("Something went wrong!!!Please try again");
		else
			return true;
	}

	public List<VisitorRequest> getAllByStatus(String status) throws BusinessException {
		List<VisitorRequest> visitorRequestList = visitorRequestRepository.findAllByStatus(status);
		if (visitorRequestList == null)
			throw new BusinessException("No Requests Raised!!");
		else
			return visitorRequestList;
	}

	public boolean approveOdcRequest(VisitorRequest visitorRequest) throws BusinessException {
		visitorRequestRepository.deleteByEmpIdAndStatus(visitorRequest.getEmpId(), visitorRequest.getStatus());
		visitorRequest.setStatus("Approved");
		VisitorRequest success = visitorRequestRepository.save(visitorRequest);
		if (success == null)
			throw new BusinessException("Request Cannot be Approved");
		else
			return true;
	}

	public boolean rejectOdcRequest(VisitorRequest visitorRequest) throws BusinessException {
		visitorRequestRepository.deleteByEmpIdAndStatus(visitorRequest.getEmpId(), visitorRequest.getStatus());
		visitorRequest.setStatus("Rejected");
		VisitorRequest success = visitorRequestRepository.save(visitorRequest);
		if (success == null)
			throw new BusinessException("Request Cannot be Rejected");
		else
			return true;
	}

}
