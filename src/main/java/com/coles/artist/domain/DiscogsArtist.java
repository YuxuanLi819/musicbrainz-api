package com.coles.artist.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Discogs Artist response domain
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class DiscogsArtist {

	private String id;

	private String profile;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}
}
