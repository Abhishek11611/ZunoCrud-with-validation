package com.example.demo.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.entity.NominieeDetailsEntity;
import com.example.demo.entity.PersonalDetailsEntity;
import com.example.demo.pagination.PersonalDetailsListing;
import com.example.demo.pagination.PersonalDetailsSearch;
import com.example.demo.repository.NomineeDetailsRepository;
import com.example.demo.repository.PersonalDetailsRepository;
import com.example.demo.requestdto.NomineeRequestDto;
import com.example.demo.requestdto.PdRequestDto;
import com.example.demo.requestdto.PdRequiredDto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class PersonalDetailsServiceImpl implements PersonalDetailsService {

	@Autowired
	private PersonalDetailsRepository personalDetailsRepository;

	@Autowired
	private NomineeDetailsRepository nomineeDetailsRepository;
	
	@Autowired
	private EntityManager entityManager;
	
//================================================= Add proposal ===========================================================================
	@Override
	public String addPerson(PdRequestDto pdRequestDto) {

		PersonalDetailsEntity pdobj = new PersonalDetailsEntity();
		List<String> errors = new ArrayList<>();

		if (pdRequestDto.getPersonTilte() == null || pdRequestDto.getPersonTilte().toString().isEmpty()) {
			errors.add("Title is missing");
		}

		if (pdRequestDto.getPersonFirstName() == null || pdRequestDto.getPersonFirstName().isEmpty()) {
			errors.add("FirstName is missing");
		}

		if (pdRequestDto.getPersonGender() == null || pdRequestDto.getPersonGender().toString().isEmpty()) {
			errors.add("Gender is missing");
		}

		if (pdRequestDto.getPersonDateOfBirth() == null || pdRequestDto.getPersonDateOfBirth().toString().isEmpty()) {
			errors.add("Date of Birth is missing");
		}

		if (pdRequestDto.getPersonPanNumber() == null || pdRequestDto.getPersonPanNumber().isEmpty()) {
			errors.add("PAN Number is missing");
		} else if (pdRequestDto.getPersonPanNumber().length() != 10
				|| !pdRequestDto.getPersonPanNumber().matches("[A-Z]{5}[0-9]{4}[A-Z]{1}")) {
			errors.add("Please enter a valid PAN Number");
		} else if (personalDetailsRepository.existsByPersonPanNumber(pdRequestDto.getPersonPanNumber())) {
			errors.add("Pancard Already Existing");
		}

		if (pdRequestDto.getPersonAadhaarNumber() == null
				|| pdRequestDto.getPersonAadhaarNumber().toString().isEmpty()) {
			errors.add("Aadhaar Number is missing");
		} else if (pdRequestDto.getPersonAadhaarNumber().toString().length() != 12) {
			errors.add("Please enter a 12-digit Aadhaar Number");
		} else if (personalDetailsRepository.existsByPersonAadhaarNumber(pdRequestDto.getPersonAadhaarNumber())) {
			errors.add("Aadhar Already Existing");
		}

		if (pdRequestDto.getPersonMaritalStatus() == null) {
			errors.add("Marital Status is missing");
		}

		if (pdRequestDto.getPersonEmail() == null) {
			errors.add("Email is missing");
		} else if (personalDetailsRepository.existsByPersonEmail(pdRequestDto.getPersonEmail())) {
			errors.add("Email Already Existing");

		}

		if (pdRequestDto.getPersonMobileNo() == null) {
			errors.add("Mobile Number is missing");
		} else if (personalDetailsRepository.existsByPersonMobileNo(pdRequestDto.getPersonMobileNo())) {
			errors.add("Mobile Number Already Existing");
		}

		if (pdRequestDto.getPersonAlternateMobileNo() == null) {
			errors.add("Alternative Mobile Number is missing");
		}

		if (pdRequestDto.getPersonAddress1() == null) {
			errors.add("Address1 is missing");
		}

		if (pdRequestDto.getPersonAddress2() == null) {
			errors.add("Address2 is missing");
		}

		if (pdRequestDto.getPersonAddress3() == null) {
			errors.add("Address3 is missing");
		}

		if (pdRequestDto.getPersonPincode() == null) {
			errors.add("Pincode is missing");
		}

		if (pdRequestDto.getPersonCity() == null) {
			errors.add("City is missing");
		}

		if (pdRequestDto.getPersonState() == null) {
			errors.add("State is missing");
		}

		if (!errors.isEmpty()) {
			throw new IllegalArgumentException(String.join(", ", errors));
		}

		pdobj.setPersonTilte(pdRequestDto.getPersonTilte());
//	    pdobj.setPersonFullName(pdRequestDto.getPersonFullName());
		pdobj.setPersonFirstName(pdRequestDto.getPersonFirstName());
		pdobj.setPersonMiddleName(pdRequestDto.getPersonMiddleName());
		pdobj.setPersonLastName(pdRequestDto.getPersonLastName());
		pdobj.setPersonGender(pdRequestDto.getPersonGender());
		pdobj.setPersonDateOfBirth(pdRequestDto.getPersonDateOfBirth());
		pdobj.setPersonPanNumber(pdRequestDto.getPersonPanNumber());
		pdobj.setPersonAadhaarNumber(pdRequestDto.getPersonAadhaarNumber());
		pdobj.setPersonMaritalStatus(pdRequestDto.getPersonMaritalStatus());
		pdobj.setPersonEmail(pdRequestDto.getPersonEmail());
		pdobj.setPersonMobileNo(pdRequestDto.getPersonMobileNo());
		pdobj.setPersonAlternateMobileNo(pdRequestDto.getPersonAlternateMobileNo());
		pdobj.setPersonAddress1(pdRequestDto.getPersonAddress1());
		pdobj.setPersonAddress2(pdRequestDto.getPersonAddress2());
		pdobj.setPersonAddress3(pdRequestDto.getPersonAddress3());
		pdobj.setPersonPincode(pdRequestDto.getPersonPincode());
		pdobj.setPersonCity(pdRequestDto.getPersonCity());
		pdobj.setPersonState(pdRequestDto.getPersonState());

		PersonalDetailsEntity personalid = personalDetailsRepository.save(pdobj);

		NomineeRequestDto nomineeDetail = pdRequestDto.getNomineeDetails();

		List<NominieeDetailsEntity> list = new ArrayList<>();


			NominieeDetailsEntity nominee = new NominieeDetailsEntity();

			nominee.setNomineeName(nomineeDetail.getNomineeName());
			nominee.setNomineeNumber(nomineeDetail.getNomineeNumber());
			nominee.setNomineeDateOfBirth(nomineeDetail.getNomineeDateOfBirth());
			nominee.setNomineeGender(nomineeDetail.getNomineeGender());
			nominee.setNomineeRelationship(nomineeDetail.getNomineeRelationship());
			nominee.setPersonId(personalid.getPersonId());

			list.add(nominee);
		

		nomineeDetailsRepository.saveAll(list);

		return "Person And Nominee Added Successfully";
	}
	
//	==================================================== GetAll proposal ByPageable =============================================================

//	@Override
//	public List<PdRequiredDto> getAllPersonDetails(Integer pageNumber, Integer pageSize) {
//		
//		Pageable p = PageRequest.of(pageNumber, pageSize);
//		
//		Page<PersonalDetailsEntity> pageresult = personalDetailsRepository.findByStatus("Yes",p);
//		
//		List<PersonalDetailsEntity> perlist = pageresult.getContent();
//				
//		List<PdRequiredDto> dtobj = new ArrayList<>();
//
//		for (PersonalDetailsEntity personalDetailsEntity : perlist) {
//
//			PdRequiredDto dtoobj = new PdRequiredDto();
//
//			dtoobj.setPersonTilte(personalDetailsEntity.getPersonTilte());
//			dtoobj.setPersonGender(personalDetailsEntity.getPersonGender());
//			dtoobj.setPersonDateOfBirth(personalDetailsEntity.getPersonDateOfBirth());
//			dtoobj.setPersonAddress1(personalDetailsEntity.getPersonAddress1());
//			dtoobj.setPersonAadhaarNumber(personalDetailsEntity.getPersonAadhaarNumber());
//			dtoobj.setPersonAddress1(personalDetailsEntity.getPersonAddress1());
//			dtoobj.setPersonAddress2(personalDetailsEntity.getPersonAddress2());
//			dtoobj.setPersonAddress3(personalDetailsEntity.getPersonAddress3());
//			dtoobj.setPersonMobileNo(personalDetailsEntity.getPersonMobileNo());
//			dtoobj.setPersonAlternateMobileNo(personalDetailsEntity.getPersonAlternateMobileNo());
//			dtoobj.setPersonPincode(personalDetailsEntity.getPersonPincode());
//			dtoobj.setPersonCity(personalDetailsEntity.getPersonCity());
//			dtoobj.setPersonState(personalDetailsEntity.getPersonState());
//			dtoobj.setStatus(personalDetailsEntity.getStatus());
//			dtoobj.setPersonPanNumber(personalDetailsEntity.getPersonPanNumber());
//			dtoobj.setPersonMaritalStatus(personalDetailsEntity.getPersonMaritalStatus());
//			dtoobj.setPersonEmail(personalDetailsEntity.getPersonEmail());
//
////			dtoobj.setPersonFullName(personalDetailsEntity.getPersonFullName());
//
//			StringBuilder builder = new StringBuilder();
//
//			builder.append(personalDetailsEntity.getPersonFirstName() + " ");
//
//			if (personalDetailsEntity.getPersonMiddleName() != null) {
//				builder.append(personalDetailsEntity.getPersonMiddleName());
//			}
//			builder.append(" " + personalDetailsEntity.getPersonLastName());
//
//			dtoobj.setPersonFullName(builder.toString());
//
//			dtobj.add(dtoobj);
//		}
//
//		return dtobj;
//
//	}
//===================================================== GetAll proposal ByStringBuilder =================================================================

	
	@Override
	public List<PersonalDetailsEntity> fetchAllByStringbuilder(PersonalDetailsListing personalDetailsListing) {

	    StringBuilder builder = new StringBuilder("select p from PersonalDetailsEntity p where p.status = 'Yes'");

	    
	    PersonalDetailsSearch search = personalDetailsListing.getPersonalDetailsSearch();
	    
	    if (search == null) {
	        search = new PersonalDetailsSearch();
	        personalDetailsListing.setPersonalDetailsSearch(search);
	    }
	    
		String firstName = search.getPersonFirstName();
		String lastName = search.getPersonLastName();
		Long mobileNo = search.getPersonMobileNo();

	    
	    if (search != null) {
	    	
	        if (firstName != null && !firstName.isEmpty()) {
	        	builder.append(" and lower(p.personFirstName) like '%").append(firstName.toLowerCase()).append("%'");
	        }
	        
	        if(lastName!=null && !lastName.isEmpty()) {
	        	builder.append(" and lower(personLastName) like '%").append(lastName.toLowerCase()).append("%'");
	        }
	        
	        if(mobileNo!=null && !mobileNo.toString().isEmpty() && mobileNo!=0) {
	        	builder.append(" and personMobileNo =").append(mobileNo);
	        }
	        
	    }
	    
	 
	    String sortBy = personalDetailsListing.getSortBy();
	    
	    sortBy = (sortBy == null || sortBy.isEmpty()) ? "personId" : sortBy;
	    
//	    if (sortBy == null || sortBy.isEmpty()) {
//	        sortBy = "personId";
//	    }

	    String sortOrder = personalDetailsListing.getSortOrder();
	    
	    if (sortOrder == null || sortOrder.isEmpty()) {
	        sortOrder = "desc";
	    }

	    builder.append(" order by p.").append(sortBy).append(" ").append(sortOrder);
	    
	    int page = personalDetailsListing.getPageNumber();
	    int size = personalDetailsListing.getPageSize();

	    TypedQuery<PersonalDetailsEntity> query = entityManager.createQuery(builder.toString(), PersonalDetailsEntity.class);
	    
	    int startIndex = ((page - 1) * size);
	    int endIndex = startIndex + size;
	    
	    if (page > 0 && size > 0) {
	    
	        query.setFirstResult(startIndex);
	        query.setMaxResults(size);
	        
	       }else  if(page == 0 && size >0 || page > 0 && size ==0) {
	    		
	    		throw new IllegalArgumentException("page or Size can't be zero ");
	    	}
	    
	    return query.getResultList();
	}

//========================================== GetAll proposal ByCriteriaQuery =================================================================
	
	
//	@Override
//	public List<PersonalDetailsEntity> getAllPersonDetails(PersonalDetailsListing personalDetailsListing) {
//
//
//	    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//	    CriteriaQuery<PersonalDetailsEntity> cq = cb.createQuery(PersonalDetailsEntity.class);
//	    Root<PersonalDetailsEntity> root = cq.from(PersonalDetailsEntity.class);
//
//	    List<Predicate> predicates = new ArrayList<>();
//
//	    predicates.add(cb.equal(root.get("status"), "Yes"));
//
//	   
//	    PersonalDetailsSearch personalDetailsSearch = personalDetailsListing.getPersonalDetailsSearch();
//
//		String firstName = personalDetailsSearch.getPersonFirstName();
//		String lastName = personalDetailsSearch.getPersonLastName();
//		Long mobileNo = personalDetailsSearch.getPersonMobileNo();
//	    
//	    if(personalDetailsSearch != null) {
//	    	
//	    	 if ( firstName != null && !firstName.isEmpty()) {
//	 	        predicates.add(cb.like(cb.lower(root.get("personFirstName")), "%" + firstName.toLowerCase() + "%"));
//	 	    }
//
//	 	    if ( lastName != null && !lastName.isEmpty()) {
//	 	        predicates.add(cb.like(cb.lower(root.get("personLastName")), "%" + lastName.toLowerCase() + "%"));
//	 	    }
//
//	 	    if ( mobileNo != null && !mobileNo.toString().isEmpty()) {
//	 	        predicates.add(cb.equal(root.get("personMobileNo"), mobileNo));
//	 	    }
//	    }
//	    
//    
//	    cq.where(cb.and(predicates.toArray(new Predicate[0])));
//
//	    
//	    String sortBy = personalDetailsListing.getSortBy();
//	    
//	    if (sortBy == null || sortBy.isEmpty()) {
//	        sortBy = "personId";
//	    }
//
//	    String sortOrder = personalDetailsListing.getSortOrder();
//	    if (sortOrder == null || sortOrder.isEmpty()) {
//	        sortOrder = "desc";
//	    }
//
//	    if (sortOrder.equalsIgnoreCase("desc")) {
//	        cq.orderBy(cb.desc(root.get(sortBy)));
//	    } else {
//	        cq.orderBy(cb.asc(root.get(sortBy)));
//	    }
//
//	   
//	    int page = personalDetailsListing.getPageNumber();
//	    int size = personalDetailsListing.getPageSize();
//
//	    TypedQuery<PersonalDetailsEntity> query = entityManager.createQuery(cq);
//
//	    int startIndex = ((page - 1) * size);
//	    int endIndex = startIndex + size;
//	    
//	    if (page >= 0 && size > 0) {
//	    
//	        query.setFirstResult(startIndex);
//	        query.setMaxResults(size);
//	        
//	       }else  if(page == 0 && size >0 || page > 0 && size ==0) {
//	    		
//	    		throw new IllegalArgumentException("page cant be zero");
//	    	}
//    
//
//	    return query.getResultList();
//	}

	
//========================================================== Delete proposal =================================================================
	@Override
	public String deletePersonDetails(Integer personId) {

		Optional<PersonalDetailsEntity> per = personalDetailsRepository.findById(personId);

		if (per.isPresent()) {
			PersonalDetailsEntity personget = per.get();

			personget.setStatus("No");
			PersonalDetailsEntity existingProposer = personalDetailsRepository.save(personget);

			Optional<NominieeDetailsEntity> optional = nomineeDetailsRepository
					.findAllByPersonId(existingProposer.getPersonId());
			NominieeDetailsEntity existingNomineeDetailsEntities = optional.get();

//			for (int i = 0; i < existingNomineeDetailsEntities.size(); i++) {
//				existingNomineeDetailsEntities.get(i).setNomineeStatus("No");
//			}
//
//			nomineeDetailsRepository.saveAll(existingNomineeDetailsEntities);

			return "delete succesfully!!";
		}
		return "proposer not existed!!";

	}

//============================================================== Update Proposal ================================================================
	
	@Override
	public String updatepersonById(Integer personId, PdRequestDto pdRequestDto) {

		Optional<PersonalDetailsEntity> per = personalDetailsRepository.findByPersonIdAndStatus(personId, "Yes");

		if (per.isPresent()) {
						

			PersonalDetailsEntity personget = per.get();
			
            if(pdRequestDto.getPersonTilte()!=null && !pdRequestDto.getPersonTilte().toString().isEmpty()) {
    			personget.setPersonTilte(pdRequestDto.getPersonTilte());
            }
            if(pdRequestDto.getPersonGender()!=null && !pdRequestDto.getPersonGender().toString().isEmpty()) {
    			personget.setPersonGender(pdRequestDto.getPersonGender());
            }
            if(pdRequestDto.getPersonDateOfBirth()!=null && !pdRequestDto.getPersonDateOfBirth().toString().isEmpty()) {
            	personget.setPersonDateOfBirth(pdRequestDto.getPersonDateOfBirth());
            }
            if(pdRequestDto.getPersonPanNumber()!=null && !pdRequestDto.getPersonPanNumber().isEmpty()) {
    			personget.setPersonPanNumber(pdRequestDto.getPersonPanNumber());
            }
            if(pdRequestDto.getPersonAadhaarNumber()!=null && !pdRequestDto.getPersonAadhaarNumber().toString().isEmpty()) {
        		personget.setPersonAadhaarNumber(pdRequestDto.getPersonAadhaarNumber());
            }
	        if(pdRequestDto.getPersonMaritalStatus()!=null && !pdRequestDto.getPersonMaritalStatus().toString().isEmpty()) {
	        	personget.setPersonMaritalStatus(pdRequestDto.getPersonMaritalStatus());
	        }
			if(pdRequestDto.getPersonEmail()!=null && !pdRequestDto.getPersonEmail().isEmpty()) {
				personget.setPersonEmail(pdRequestDto.getPersonEmail());

			}
			if(pdRequestDto.getPersonMobileNo()!=null && !pdRequestDto.getPersonMobileNo().toString().isEmpty()) {
				personget.setPersonMobileNo(pdRequestDto.getPersonMobileNo());
			}
			if(pdRequestDto.getPersonAlternateMobileNo()!=null && !pdRequestDto.getPersonAlternateMobileNo().toString().isEmpty()) {
				personget.setPersonAlternateMobileNo(pdRequestDto.getPersonAlternateMobileNo());
			}
			if(pdRequestDto.getPersonAddress1()!=null && !pdRequestDto.getPersonAddress1().toString().isEmpty()) {
				personget.setPersonAddress1(pdRequestDto.getPersonAddress1());
			}
            if(pdRequestDto.getPersonAddress2()!=null && !pdRequestDto.getPersonAddress2().isEmpty()) {
    			personget.setPersonAddress2(pdRequestDto.getPersonAddress2());

            }
            if(pdRequestDto.getPersonAddress3()!=null && !pdRequestDto.getPersonAddress3().isEmpty()) {
    			personget.setPersonAddress3(pdRequestDto.getPersonAddress3());
            }
            if(pdRequestDto.getPersonPincode()!=null && !pdRequestDto.getPersonPincode().toString().isEmpty()) {
            	personget.setPersonPincode(pdRequestDto.getPersonPincode());
            }
			if(pdRequestDto.getPersonCity()!=null && !pdRequestDto.getPersonCity().isEmpty()) {
				personget.setPersonCity(pdRequestDto.getPersonCity());
			}
			if(pdRequestDto.getPersonState()!=null && !pdRequestDto.getPersonState().isEmpty()) {
				personget.setPersonState(pdRequestDto.getPersonState());
			}
		

			PersonalDetailsEntity detailsEnity = personalDetailsRepository.save(personget);

			NomineeRequestDto nomineedto = pdRequestDto.getNomineeDetails();

			List<NominieeDetailsEntity> nominieeDetailsEntities = new ArrayList<>();

			Optional<NominieeDetailsEntity> optional = nomineeDetailsRepository.findAllByPersonId(detailsEnity.getPersonId());
			NominieeDetailsEntity existingnominee = optional.get();
			
			if(pdRequestDto.getIsUpdate().equalsIgnoreCase("Y")) {
				existingnominee.setPersonId(detailsEnity.getPersonId());
				existingnominee.setNomineeName(nomineedto.getNomineeName());
				existingnominee.setNomineeNumber((nomineedto.getNomineeNumber()));
				existingnominee.setNomineeDateOfBirth(nomineedto.getNomineeDateOfBirth());
				existingnominee.setNomineeGender(nomineedto.getNomineeGender());
				existingnominee.setNomineeRelationship(nomineedto.getNomineeRelationship());
				
				nomineeDetailsRepository.save(existingnominee);
			}
			else {
				existingnominee.setNomineeStatus("N");
				
				nomineeDetailsRepository.save(existingnominee);
				
				NominieeDetailsEntity nominieeDetailsEntity = new NominieeDetailsEntity();
				nominieeDetailsEntity.setNomineeName(nomineedto.getNomineeName());
				nominieeDetailsEntity.setPersonId(detailsEnity.getPersonId());
				nominieeDetailsEntity.setNomineeDateOfBirth(nomineedto.getNomineeDateOfBirth());
				nominieeDetailsEntity.setNomineeGender(nomineedto.getNomineeGender());
				nominieeDetailsEntity.setNomineeNumber(nomineedto.getNomineeNumber());
				nominieeDetailsEntity.setNomineeDateOfBirth(nomineedto.getNomineeDateOfBirth());
				nominieeDetailsEntity.setNomineeRelationship(nomineedto.getNomineeRelationship());
				
				nomineeDetailsRepository.save(nominieeDetailsEntity);
			}

//			for (int i = 0; i < existingnominee.size(); i++) {
//				for (NomineeRequestDto dto1 : nomineedto) {
//
//					existingnominee.get(i).setPersonId(detailsEnity.getPersonId());
//					existingnominee.get(i).setNomineeDateOfBirth(dto1.getNomineeDateOfBirth());
//					existingnominee.get(i).setNomineeGender(dto1.getNomineeGender());
//					existingnominee.get(i).setNomineeRelationship(dto1.getNomineeRelationship());
//					existingnominee.get(i).setNomineeName(dto1.getNomineeName());
//					existingnominee.get(i).setNomineeNumber(dto1.getNomineeNumber());
//					if (i == 0) {
//						break;
//					}
//
//				}
//
//				nominieeDetailsEntities.add(existingnominee.get(i));
//			}
			
			

//			nomineeDetailsRepository.saveAll(nominieeDetailsEntities);

			return "Person Details Update Succesfully!!";
		} else {
			return "Person Details not updated";
		}

	}

//================================================================= FindBy Id Proposal =======================================================
	
	@Override
	public PdRequestDto findbyidPersonDetails(Integer personId) {

		Optional<PersonalDetailsEntity> per = personalDetailsRepository.findById(personId);
		PdRequestDto pddto = new PdRequestDto();

		if (per.isPresent()) {

			PersonalDetailsEntity perdetails = per.get();

			pddto.setPersonFirstName(perdetails.getPersonFirstName());
			pddto.setPersonEmail(perdetails.getPersonEmail());
			pddto.setPersonMiddleName(perdetails.getPersonMiddleName());
			pddto.setPersonAadhaarNumber(perdetails.getPersonAadhaarNumber());
//			pddto.setPersonMiddleName(perdetails.getPersonMiddleName());
			pddto.setPersonAddress1(perdetails.getPersonAddress1());
			pddto.setPersonAlternateMobileNo(perdetails.getPersonAlternateMobileNo());
			pddto.setPersonCity(perdetails.getPersonCity());
			pddto.setPersonDateOfBirth(perdetails.getPersonDateOfBirth());
			pddto.setPersonMiddleName(perdetails.getPersonMiddleName());
			pddto.setPersonLastName(perdetails.getPersonLastName());
			pddto.setPersonAddress2(perdetails.getPersonAddress2());
			pddto.setPersonLastName(perdetails.getPersonLastName());
			pddto.setPersonGender(perdetails.getPersonGender());
			pddto.setPersonPanNumber(perdetails.getPersonPanNumber());
			pddto.setPersonMaritalStatus(perdetails.getPersonMaritalStatus());
			pddto.setPersonMobileNo(perdetails.getPersonMobileNo());
			pddto.setPersonAddress3(perdetails.getPersonAddress3());
			pddto.setPersonPincode(perdetails.getPersonPincode());
			pddto.setPersonTilte(perdetails.getPersonTilte());
			pddto.setPersonState(perdetails.getPersonState());

			Integer proposerId = perdetails.getPersonId();

			Optional<NominieeDetailsEntity> nomieobj = nomineeDetailsRepository.findByPersonIdAndNomineeStatus(personId,"Yes");
			
			NominieeDetailsEntity existingnominee = nomieobj.get();
			
			NomineeRequestDto nomidto = new NomineeRequestDto();
			
		
			
			nomidto.setNomineeName(existingnominee.getNomineeName());
			nomidto.setNomineeNumber(existingnominee.getNomineeNumber());
			nomidto.setNomineeRelationship(existingnominee.getNomineeRelationship());
			nomidto.setNomineeDateOfBirth(existingnominee.getNomineeDateOfBirth());
			nomidto.setNomineeGender(existingnominee.getNomineeGender());
			nomidto.setPersonId(personId);
			
			
			

//			for (NominieeDetailsEntity nominieeDetailsEntity : nomieobj) {
//
//				NomineeRequestDto nomidto = new NomineeRequestDto();
//
//				nomidto.setNomineeName(nominieeDetailsEntity.getNomineeName());
//				nomidto.setNomineeDateOfBirth(nominieeDetailsEntity.getNomineeDateOfBirth());
//				nomidto.setNomineeGender(nominieeDetailsEntity.getNomineeGender());
//				nomidto.setNomineeNumber(nominieeDetailsEntity.getNomineeNumber());
//				nomidto.setNomineeRelationship(nominieeDetailsEntity.getNomineeRelationship());
//				nomidto.setPersonId(nominieeDetailsEntity.getPersonId());
//
//				list.add(nomidto);
//			}

			pddto.setNomineeDetails(nomidto);

		}
		return pddto;
	}

//==================================================== Page Size ======================================================================
	
	@Override
	public Integer countAllProposal() {
		List<PersonalDetailsEntity> getallRecord = personalDetailsRepository.findByStatus("Yes");
		return getallRecord.size();
	}

	
	
//=================================================== ExportExcel ===================================================================
	
	@Override
	public void generateExcel(HttpServletResponse response) throws IOException {
		
		List<PersonalDetailsEntity> getalldata = personalDetailsRepository.findAll();
		
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Proposal Info");
		HSSFRow row = sheet.createRow(0);
		
		row.createCell(0).setCellValue("personId");
		row.createCell(1).setCellValue("personFirstName");
		row.createCell(2).setCellValue("personMiddleName");
		row.createCell(3).setCellValue("personLastName");
		row.createCell(4).setCellValue("personGender");
		row.createCell(5).setCellValue("personDateOfBirth");
		row.createCell(6).setCellValue("personPanNumber");
		row.createCell(7).setCellValue("personAadhaarNumber");
		row.createCell(8).setCellValue("personMaritalStatus");
		row.createCell(9).setCellValue("personEmail");
		row.createCell(10).setCellValue("personAlternateMobileNo");
		row.createCell(11).setCellValue("personAddress1");
		row.createCell(12).setCellValue("personAddress2");
		row.createCell(13).setCellValue("personAddress3");
		row.createCell(14).setCellValue("personPincode");
		row.createCell(15).setCellValue("personCity");
		row.createCell(16).setCellValue("personState");
		row.createCell(17).setCellValue("status");
		row.createCell(18).setCellValue("personMobileNo");
		
		int dataRowIndex = 1;
		
		for(PersonalDetailsEntity personalDetailsEntity : getalldata) {
			HSSFRow dataRow = sheet.createRow(dataRowIndex);
			
			dataRow.createCell(0).setCellValue(personalDetailsEntity.getPersonId());
			dataRow.createCell(1).setCellValue(personalDetailsEntity.getPersonFirstName());
			dataRow.createCell(2).setCellValue(personalDetailsEntity.getPersonMiddleName());
			dataRow.createCell(3).setCellValue(personalDetailsEntity.getPersonLastName());
			dataRow.createCell(4).setCellValue(personalDetailsEntity.getPersonGender().toString());
			dataRow.createCell(5).setCellValue(personalDetailsEntity.getPersonDateOfBirth());
			dataRow.createCell(6).setCellValue(personalDetailsEntity.getPersonPanNumber());
			dataRow.createCell(7).setCellValue(personalDetailsEntity.getPersonAadhaarNumber());
			dataRow.createCell(8).setCellValue(personalDetailsEntity.getPersonMaritalStatus().toString());
			dataRow.createCell(9).setCellValue(personalDetailsEntity.getPersonEmail());
			dataRow.createCell(10).setCellValue(personalDetailsEntity.getPersonAlternateMobileNo());
			dataRow.createCell(11).setCellValue(personalDetailsEntity.getPersonAddress1());
			dataRow.createCell(12).setCellValue(personalDetailsEntity.getPersonAddress2());
			dataRow.createCell(13).setCellValue(personalDetailsEntity.getPersonAddress3());
			dataRow.createCell(14).setCellValue(personalDetailsEntity.getPersonPincode());
			dataRow.createCell(15).setCellValue(personalDetailsEntity.getPersonCity());
			dataRow.createCell(16).setCellValue(personalDetailsEntity.getPersonState());
			dataRow.createCell(17).setCellValue(personalDetailsEntity.getStatus());
			dataRow.createCell(18).setCellValue(personalDetailsEntity.getPersonMobileNo());
			
			dataRowIndex ++;
			
		
			
			
		}
		
		ServletOutputStream ops = response.getOutputStream();
		workbook.write(ops);
		workbook.close();
		ops.close();
		
		
	}

	

}
