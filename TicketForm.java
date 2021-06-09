package com.jumpy.Cinema.web;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class TicketForm {
	private String nomClient;
	private List<Long>tickets=new ArrayList<>();

}
