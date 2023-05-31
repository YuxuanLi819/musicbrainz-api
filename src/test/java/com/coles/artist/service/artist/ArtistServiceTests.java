package com.coles.artist.service.artist;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import com.coles.artist.domain.Artist;
import com.coles.artist.exception.ArtistNotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.coles.artist.MusicBrainzAPIApplicationTests;
import com.coles.artist.domain.ReleaseGroup;

/**
 * Artist service test cases
 */
public class ArtistServiceTests extends MusicBrainzAPIApplicationTests {

	@Autowired
	private ArtistService artistService;

	/**
	 * 
	 * get Eminem info and check response
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetZaraLarssonInfo_checkResponse() throws Exception {
		// call service for Eminem
		Artist artist = artistService.getArtistInfo("b95ce3ff-3d05-4e87-9e01-c97b66af13d4").get();

		assertNotNull(artist);
		// check id
		assertNotNull(artist.getId());
		assertFalse(artist.getId().isEmpty());
		// check description
		assertNotNull(artist.getDescription());
		assertFalse(artist.getDescription().isEmpty());
		assertTrue(artist.getDescription().contains("American rapper"));
		// check release groups
		List<ReleaseGroup> releaseGroups = artist.getReleaseGroups();
		assertNotNull(releaseGroups);
		assertFalse(releaseGroups.isEmpty());
		assertEquals(25, releaseGroups.size());

		releaseGroups.forEach(releaseGroup -> {
			// check id
			assertNotNull(releaseGroup.getId());
			assertFalse(releaseGroup.getId().isEmpty());
			// check title
			assertNotNull(releaseGroup.getTitle());
			assertFalse(releaseGroup.getTitle().isEmpty());
			// check image
			assertNotNull(releaseGroup.getImage());
			assertFalse(releaseGroup.getImage().isEmpty());

			// check values
			switch (releaseGroup.getTitle()) {
			case "Infinite":
				assertEquals("http://coverartarchive.org/release/13149c69-857f-4e89-83c3-da8f19ad5c4c/11149469508.jpg",
						releaseGroup.getImage());
				break;
			case "The Slim Shady LP":
				assertEquals("http://coverartarchive.org/release/ede60c69-29d1-48dd-a10b-169ae99dee92/27043383600.jpg",
						releaseGroup.getImage());
				break;
			default:
				break;
			}
		});
	}

	@Test
	public void testGetNotFoundArtist_checkResponse() throws Exception {
		// try to get not found artist
		try {
			// wait to finish and get result
			artistService.getArtistInfo("xyz").get();
			fail();
		} catch (Exception e) {
			assertTrue(e.getCause() instanceof ArtistNotFoundException);
		}
	}
}
