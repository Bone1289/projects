package guru.springframework.msscbeerservice.services;

import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.repositories.BeerRepository;
import guru.springframework.msscbeerservice.web.controller.NotFoundException;
import guru.springframework.msscbeerservice.web.mappers.BeerMapper;
import guru.springframework.msscbeerservice.web.model.BeerDto;
import guru.springframework.msscbeerservice.web.model.BeerPagedList;
import guru.springframework.msscbeerservice.web.model.BeerStyleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by jt on 2019-06-06.
 */
@RequiredArgsConstructor
@Service
public class BeerServiceImpl implements BeerService {
	private final BeerRepository beerRepository;
	private final BeerMapper beerMapper;


	@Cacheable(cacheNames = "beerListCache", condition = "#showOnhandInventory== false")
	@Override public BeerPagedList listBeer(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest,
			boolean showOnhandInventory) {
		BeerPagedList beerPagedList;

		Page<Beer> beerPage;

		if (!StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle)) {
			beerPage = beerRepository.findAllByBeerNameAndBeerStyle(beerName, beerStyle, pageRequest);
		} else if (!StringUtils.isEmpty(beerName) && StringUtils.isEmpty(beerStyle)) {
			beerPage = beerRepository.findAllByBeerName(beerName, pageRequest);
		} else if (StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle)) {
			beerPage = beerRepository.findAllByBeerStyle(beerStyle, pageRequest);
		} else {
			beerPage = beerRepository.findAll(pageRequest);
		}
		Pageable beerPageable = beerPage.getPageable();

		beerPagedList = new BeerPagedList(beerPage.getContent().stream()
				.map(beer -> showOnhandInventory ? beerMapper.beerToBeerDto(beer) :
						beerMapper.beerToBeerDtoWithInventory(beer))
				.collect(Collectors.toList()),
				PageRequest.of(beerPageable.getPageNumber(), beerPageable.getPageSize()),
				beerPage.getTotalElements()
		);

		return beerPagedList;
	}
	@Cacheable(cacheNames = "beerCache",
			key = "#beerId",condition = "#showInventoryOnhand== false")
	@Override
	public BeerDto getById(UUID beerId, boolean showInventoryOnhand) {
		Beer beer = beerRepository.findById(beerId).orElseThrow(NotFoundException::new);
		return showInventoryOnhand ? beerMapper.beerToBeerDto(beer) :
				beerMapper.beerToBeerDtoWithInventory(beer);
	}

	@Override
	public BeerDto saveNewBeer(BeerDto beerDto) {
		return beerMapper.beerToBeerDto(beerRepository.save(beerMapper.beerDtoToBeer(beerDto)));
	}

	@Override
	public BeerDto updateBeer(UUID beerId, BeerDto beerDto) {
		Beer beer = beerRepository.findById(beerId).orElseThrow(NotFoundException::new);

		beer.setBeerName(beerDto.getBeerName());
		beer.setBeerStyle(beerDto.getBeerStyle().name());
		beer.setPrice(beerDto.getPrice());
		beer.setUpc(beerDto.getUpc());

		return beerMapper.beerToBeerDto(beerRepository.save(beer));
	}
}
