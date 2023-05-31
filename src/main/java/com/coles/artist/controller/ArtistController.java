package com.coles.artist.controller;

import java.util.concurrent.CompletableFuture;

import com.coles.artist.controller.mapper.ArtistMapper;
import com.coles.artist.datatransferobject.ArtistDTO;
import com.coles.artist.domain.Artist;
import com.coles.artist.exception.ArtistNotFoundException;
import com.coles.artist.exception.ServiceNotAvailableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.coles.artist.service.artist.ArtistService;

/**
 * All operations with an artist will be routed by this controller.
 */
@RestController
public class ArtistController {

	private static final Logger logger = LoggerFactory.getLogger(ArtistController.class);

	private final ArtistService artistService;

	@Autowired
	public ArtistController(final ArtistService artistService) {
		this.artistService = artistService;
	}

	@GetMapping("/artist/{mbid}")
	public ArtistDTO getArtistInfo(@PathVariable String mbid)
			throws ArtistNotFoundException, ServiceNotAvailableException {

		logger.debug("Called ArtistController.getArtistInfo with MBID :{}", mbid);

		// create CompletableFuture thread to get artist info
		CompletableFuture<Artist> artistFuture = artistService.getArtistInfo(mbid);

		Artist artist = null;
		try {
			// wait to finish and get result
			artist = artistFuture.get();
		} catch (Exception e) {
			logger.error("Error while getting future thread result of artist with message {}", e.getMessage());
			// check ArtistNotFoundException
			if(e.getCause() instanceof ArtistNotFoundException)
				throw new ArtistNotFoundException();
			
			throw new ServiceNotAvailableException();
		}

		return ArtistMapper.makeArtistDTO(artist);
	}
}
