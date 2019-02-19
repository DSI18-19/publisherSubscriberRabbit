/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cat.tecnocampus.timesender;

import cat.tecnocampus.timesender.configuration.SenderChannels;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author Dave Syer
 * @author Glenn Renfro
 *
 */
@EnableBinding(SenderChannels.class)
public class TimeSource {

	String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";

	@InboundChannelAdapter(value = SenderChannels.TIME_CHANNEL, poller = @Poller(fixedDelay = "${fixedDelay}",
			maxMessagesPerPoll = "1"))
	public String timerMessageSource() {
		System.out.println("Source: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern(dateTimeFormat)));
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern(dateTimeFormat));
	}
}
