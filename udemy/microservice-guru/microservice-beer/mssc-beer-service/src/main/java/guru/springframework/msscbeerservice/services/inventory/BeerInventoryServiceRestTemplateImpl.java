package guru.springframework.msscbeerservice.services.inventory;

import guru.springframework.msscbeerservice.services.inventory.model.BeerInventoryDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@ConfigurationProperties(prefix = "sfg.brewery", ignoreInvalidFields = false)
@Component
public class BeerInventoryServiceRestTemplateImpl implements BeerInventoryService {

	private final String INVENTORY_PATH = "/api/v1/beer/{beerId}/inventory";
	private final RestTemplate restTemplate;

	private String beerInventoryServiceHost;

	public BeerInventoryServiceRestTemplateImpl(RestTemplateBuilder restTemplate) {
		this.restTemplate = restTemplate.build();
	}

	public void setBeerInventoryServiceHost(String beerInventoryServiceHost) {
		this.beerInventoryServiceHost = beerInventoryServiceHost;
	}

	@Override public Integer getOnHandInventory(UUID beerId) {
		log.debug("Calling Inventory Service");

		ResponseEntity<List<BeerInventoryDto>> responseEntity =
				restTemplate.exchange(beerInventoryServiceHost + INVENTORY_PATH, HttpMethod.GET,
						null, new ParameterizedTypeReference<>() {
						}, beerId);

		return Objects.requireNonNull(
				responseEntity.getBody().stream().mapToInt(BeerInventoryDto::getQuantityOnHand).sum());
	}
}
